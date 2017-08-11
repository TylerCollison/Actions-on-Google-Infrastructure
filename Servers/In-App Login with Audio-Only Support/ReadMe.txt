This software package is a Node JS Fulfillment Server for a Bank of America Actions on Google App. This server processes user data in response to requests from the Google Assistant and responds with that data. In addition, this server implements an in-app login mechanism that seeks to replace standard Account Linking as a login technique. 

It should be noted that the server does not currently use access tokens obtained through the login mechanism to access user data. Rather, for demonstration purposes, the server is simply designed to respond to all intents by sending the access token in a response to the user. In this way, whether the server succeeded in obtaining an access token from the in-app login system can be easily assessed. 

This server relies on the correct implementation of the login mechanism in a mobile application as well, either IOS or Android. Details on such an implementation can be found on the Horizontal Engineering Wiki under Actions on Google. 

The login system is implemented on the server side by determining whether the user is signed-in, sending a deep link for sign-in (if needed), and receiving a POST request from a client application with access token credentials for the user. 

The root endpoint is able to detect the capabilities of the device being used with the Google Assistant. Specifically, it is able to determine whether the device is audio-only. In the case of audio-only devices, the user must independently login (send his or her access token) either before starting a conversation or when accessing customer-specific data in a conversation. This is achieved either through a special login screen within the app or a login widget on IOS and Android. When the user logs into an audio-only device remotely, the access token for that user is associated with the Google account ID for that user. When a conversation is found under that Google ID, it is bound to the access token, and no other conversation may access that access token. In this way, a user can log into an audio-only device using his or her mobile device. 

This server consists of 5 endpoints: 
	-The root endpoint is used to receive requests and send responses to Actions on Google
	-The "/authenticate" route is used to receive access tokens from client applications and associate them with conversations
	-The "/login" route is used to redirect a web browser to a custom URL scheme deep link, in the case that an HTTP protocol deep link fails
	-The "/setup" route is used to send a deep link to a device with a screen capability (IOS or Android) to setup remote login for audio-only devices from that device
	-The "/status" route is used to determine the login status of the user, which may be "logged_out" if the user is not logged in, "ready" if the user is logged in and waiting for a conversation, or "talking" if the user is logged in and engaged in a conversation

The ADDRESS constant of the server must correspond to this server's web-accessible address.

Dependency Modules:
	-Node JS
		-http
	-NPM
		-actions-on-google
		-express
		-body-parser 
This software package is a Node JS Fulfillment Server for a Bank of America Actions on Google App. This server processes user data in response to requests from the Google Assistant and responds with that data. In addition, this server implements an in-app login mechanism that seeks to replace standard Account Linking as a login technique. 

It should be noted that the server does not currently use access tokens obtained through the login mechanism to access user data. Rather, for demonstration purposes, the server is simply designed to respond to all intents by sending the access token in a response to the user. In this way, whether the server succeeded in obtaining an access token from the in-app login system can be easily assessed. 

This server relies on the correct implementation of the login mechanism in a mobile application as well, either IOS or Android. Details on such an implementation can be found on the Horizontal Engineering Wiki under Actions on Google. 

The login system is implemented on the server side by determining whether the user is signed-in, sending a deep link for sign-in (if needed), and receiving a POST request from a client application with access token credentials for the user. 

This server consists of 3 endpoints: 
	-The root endpoint is used to receive requests and send responses to Actions on Google
	-The "/authenticate" route is used to receive access tokens from client applications and associate them with conversations
	-The "/login" route is used to redirect a web browser to a custom URL scheme deep link, in the case that an HTTP protocol deep link fails

The ADDRESS constant of the server must correspond to this server's web-accessible address.

Dependency Modules:
	-Node JS
		-http
	-NPM
		-actions-on-google
		-express
		-body-parser 
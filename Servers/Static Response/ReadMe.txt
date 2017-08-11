This software package is an Actions on Google Fulfillment Server designed to work with Account Linking. Primarily, this project simply serves as an example of how to implement a Fulfillment Server that works with Account Linking that can be deployed as a standard HTTP(S) server. As such, any hosting environment that supports Node JS can also support this Fulfillment Server as implemented.  

In addition to providing a working implementation of a Fulfillment Server running on a Firebase Function, the server can also be used to work with or test Google Account Linking. This server responds to any intent by returing a dummy account balance and a link that verifies the validity of the access token that the server received for the conversation. This link is a GET request on the OAuth server's "validate" route, which causes the record associated with the access token to be displayed in the browser for quick verification. 

Dependency Modules:
	-Node JS
		-http
	-NPM
		-actions-on-google
		-express
		-body-parser
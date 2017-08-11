This software package is an Actions on Google Fulfillment Server designed to work with Account Linking. Primarily, this project simply serves as an example of how to implement a Fulfillment Server that works with Account Linking deployed to Firebase Functions. 

Note that Firebase Functions provide a serverless hosting solution for Fulfillment Servers. As such, the Node JS implementation of the Fulfillment Server must be configured properly to run on a Firebase Function. Specifically, the code responsible for handling requests from the Actions on Google Conversation Webhook must be exported from the module as a firebase-functions.https.OnRequest object. 

In addition to providing a working implementation of a Fulfillment Server running on a Firebase Function, the server can also be used to work with or test Google Account Linking. This server responds to any intent by returing a dummy account balance and a link that verifies the validity of the access token that the server received for the conversation. This link is a GET request on the OAuth server's "validate" route, which causes the record associated with the access token to be displayed in the browser for quick verification. 

Dependency Modules:
	-NPM
		-actions-on-google
		-firebase-functions
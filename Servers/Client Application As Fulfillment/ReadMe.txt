This software package is a Fulfillment Server for a Bank of America Actions on Google App. In addition to processing data in response to user requests and responding to the Google Assistant, this server implements a specialized login system as an alternative to standard Account Linking.

Rather than process user data for response itself, this service forwards user requests to a client application linked to the user's conversation. It is then the client application's responsibility to handle user login, data acquisition, and data processing. The client then returns its response to the server, which forwards the response back to the Google Assistant. In this way, the server acts as somewhat of a proxy between the Google Assistant and the client application. In practice, only Android applications are viable for such a system. 

This server implements only one route (the root), but handles two protocols on that route: HTTP and Websocket. The HTTP protocol is used to receive requests from the Google Assistant and respond to those requests. The Websocket protocol is used to communicate between the server and the client application. 

The conversation between the client application and the server must follow a strict order and format. The conversation begins with a registration request from the client application in JSON format (see RegistrationExample.json for format). After registration, all communications from the client application are ignored until the server sends a query request to it (see QueryExample.json for format). Upon receipt of a query, the client application processes the provided user data and sends a response back to the server (see ResponseExample.json for format), which the server then passes on to the Google Assistant. This query and response paradigm is continued until the conversation is over. 

Dependency Modules:
	-Node JS
		-http
	-NPM
		-actions-on-google
		-express
		-body-parser
		-ws
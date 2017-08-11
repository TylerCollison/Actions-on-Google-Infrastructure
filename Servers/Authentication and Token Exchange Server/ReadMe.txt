This software package is a Node JS HTTP Server that implements the OAuth 2.0 Authorization Code Flow for interaction with Google APIs. Specifically, this server was designed to accomplish account linking between a Google Account and Bank of America Online Account for the purpose of passing access tokens to the Bank of America Actions on Google App. 

The server is currently implemented with a dummy database of users, authorization codes, refresh tokens, and access tokens for testing and evaluation purposes. However, all authorization codes, refresh tokens, and access tokens are encrypted. Likewise, access tokens and authorization codes expire according to industry standard timelines for this flow. The database is implemented simply with JSON files stored alongside the server and should be replaced with a more robust database solution.

Test Account:
	-username: johndoe
	-password: 12345

This server provides all needed OAuth 2.0 endpoints necessary to complete the flow from end-to-end: 
	-The "/auth" route is used as the Authorization Endpoint (Google directs the user here for sign-in). 

	-The "/exchange" route is used as the Token Exchange Endpoint, which Google Servers use to exchange Authorization Codes and Refresh Tokens or Access Tokens with POST requests. 

	-The Server also possesses a "/validate" route, which can be used to determine whether an Access Token is valid with a GET request. 

	-The "/login" route is used to communicate login credentials (username and password) between the server and its front-end, but can be used to login from any HTTP POST source with a valid user token. 

There is also a basic front-end (HTML and Javascript) packaged with the server. When deployed, the front-end presents a login screen that communicates user credentials to the backend server for validation. 

Note that this server currently implements the HTTP protocol, rather than HTTPS. For production purposes, the server should be modified to support only HTTPS, which can be accomplished trivially using standard Node JS libraries and very minor changes to the code. 

The settings.json file included in the root of the app folder contains the configuration for the server. 

Dependency Modules:
	-Node JS
		-http
		-fs
		-path
		-crypto
	-NPM
		-express
		-body-parser
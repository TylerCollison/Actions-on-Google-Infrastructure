"use strict";
const ApiAiApp = require("actions-on-google").ApiAiApp;
const functions = require('firebase-functions');

//Constant link to the OAuth server validation endpoint
const API_URL = "https://aog-oauth2-server.herokuapp.com/validate";

/**
 * Handle HTTP POST requests sent from the Actions on Google and API.AI Webhook
 */
exports.boaFulfillment = functions.https.onRequest((req, res) => {
    //Wrap the HTTP request in an ApiAiApp object
    const app = new ApiAiApp({request: req, response: res});

    //Intent for the get-balance action
    const BALANCE_INTENT = "input.balance";

    /**
     * Sends a static (dummy) response to fulfillment requests along with a link to validate the access token
     * @param {The ApiAiApp through which to communicate} app 
     */
    var balanceIntent = function(app) {
        //Retrieve access token
        let token = app.getUser().access_token;
        console.log("Access Token: " + token.toString());

        //Determine whether additional information is required

        //Collect any additional user information as necessary
        //app.ask('Question Here');

        //Use access token to request user accoutn balance from Bank of America Web API

        //Retrieve account balance information and format into text/speech response

        //Respond
        var response = ApiAiApp.prototype.buildRichResponse();
        response.addSimpleResponse("Your balance is $634.25");
        //Include link to validate the access token, for testing and evaluation purposes
        response.addSuggestionLink("Validate Access Token", API_URL + "?access_token=" + token.toString());
        app.tell(response);
    }

    //let actionMap = new Map();
    //actionMap.set(BALANCE_INTENT, balanceIntent);
    //app.handleRequest(actionMap);
    app.handleRequest(balanceIntent);
});
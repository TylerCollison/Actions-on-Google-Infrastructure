package com.bankofamerica.boatestapplication.JSON;

import com.google.gson.Gson;

/**
 * A JSON serializable object for sending user authentication requests to the server
 *
 * Created by TYLER on 7/17/2017.
 */
public class AuthenticationRequest implements IRequest {
    public String conversation_id = "";
    //TODO: acquire the access token on the user's behalf
    //This access token is a dummy value provided for demonstration purposes
    //This value should be replaced by a meaningful access token
    public String access_token = "androidapp12345";

    public AuthenticationRequest(String converseId) {
        conversation_id = converseId;
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void populateFromJsonString(String json) {
        Gson gson = new Gson();
        AuthenticationRequest req = gson.fromJson(json, this.getClass());
        conversation_id = req.conversation_id;
        access_token = req.access_token;
    }
}

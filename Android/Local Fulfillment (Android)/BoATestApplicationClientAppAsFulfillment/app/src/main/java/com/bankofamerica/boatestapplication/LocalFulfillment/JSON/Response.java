package com.bankofamerica.boatestapplication.LocalFulfillment.JSON;

import com.google.gson.Gson;

/**
 * A JSON serializable object for sending query responses to the online fulfillment server
 *
 * Created by Tyler Collison on 7/10/2017.
 */
public class Response implements IRequest {
    public String conversationId = "";
    public String type = "response";
    public String response = "";

    public Response(String converseId, String res) {
        conversationId = converseId;
        response = res;
    }

    @Override
    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public void populateFromJson(String json) {
        Gson gson = new Gson();
        Response res = gson.fromJson(json, this.getClass());
        conversationId = res.conversationId;
        type = res.type;
        response = res.response;
    }
}

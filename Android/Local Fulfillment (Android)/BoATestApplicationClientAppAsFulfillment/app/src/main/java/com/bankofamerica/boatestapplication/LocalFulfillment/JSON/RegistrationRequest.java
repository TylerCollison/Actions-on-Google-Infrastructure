package com.bankofamerica.boatestapplication.LocalFulfillment.JSON;

import com.google.gson.Gson;

/**
 * A JSON serializable object for sending a registration request to the online fulfillment server
 *
 * Created by Tyler Collison on 7/10/2017.
 */
public class RegistrationRequest implements IRequest {
    public String conversationId = "";
    public String type = "register";

    public RegistrationRequest(String converseId) {
        conversationId = converseId;
    }

    @Override
    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public void populateFromJson(String json) {
        Gson gson = new Gson();
        RegistrationRequest reg = gson.fromJson(json, this.getClass());
        conversationId = reg.conversationId;
        type = reg.type;
    }
}

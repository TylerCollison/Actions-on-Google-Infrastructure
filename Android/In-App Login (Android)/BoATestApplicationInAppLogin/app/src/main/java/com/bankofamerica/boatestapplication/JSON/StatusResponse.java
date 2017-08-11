package com.bankofamerica.boatestapplication.JSON;

import com.google.gson.Gson;

/**
 * A JSON serializable object for receiving check-status responses from the server
 *
 * Created by TYLER on 8/2/2017.
 */
public class StatusResponse implements IRequest{
    public String status = "";

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void populateFromJsonString(String json) {
        Gson gson = new Gson();
        StatusResponse req = gson.fromJson(json, this.getClass());
        status = req.status;
    }
}

package com.bankofamerica.boatestapplication.LocalFulfillment.JSON;

import com.google.gson.Gson;

/**
 * A JSON serializable object for receiving query requests from the online fulfillment server
 *
 * Created by Tyler Collison on 7/10/2017.
 */
public class Query implements IRequest {
    public String query = "";

    public Query() {}

    public Query(String json) {
        populateFromJson(json);
    }

    @Override
    public String toJsonString() {
        return null;
    }

    @Override
    public void populateFromJson (String json) {
        Gson gson = new Gson();
        Query newQuery = gson.fromJson(json, Query.class);
        query = newQuery.query;
    }
}

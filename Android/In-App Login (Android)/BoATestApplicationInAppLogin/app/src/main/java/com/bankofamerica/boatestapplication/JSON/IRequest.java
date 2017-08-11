package com.bankofamerica.boatestapplication.JSON;

/**
 * A JSON serializable object for sending messages to and receiving messages from the server
 *
 * Created by TYLER on 7/27/2017.
 */
public interface IRequest {

    /**
     * Get the JSON String representation of this request
     * @return JSON String for this object
     */
    String toJsonString();

    /**
     * Populates this object with data from the JSON representation of this request
     * @param json JSON String for this object
     */
    void populateFromJsonString(String json);
}

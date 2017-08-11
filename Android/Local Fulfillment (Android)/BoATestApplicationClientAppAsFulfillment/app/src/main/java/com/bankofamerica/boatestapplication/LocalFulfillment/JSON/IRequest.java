package com.bankofamerica.boatestapplication.LocalFulfillment.JSON;

/**
 * A JSON serializable object for sending messages to and receiving messages from the server
 *
 * Created by TYLER on 7/27/2017.
 */
public interface IRequest {

    /**
     * Get the JSON String representation of this request
     * @return The JSON String representation of this object
     */
    String toJsonString();

    /**
     * Populate the properties of this request from a JSON String
     * @param json JSON String representation of the request
     */
    void populateFromJson (String json);

}

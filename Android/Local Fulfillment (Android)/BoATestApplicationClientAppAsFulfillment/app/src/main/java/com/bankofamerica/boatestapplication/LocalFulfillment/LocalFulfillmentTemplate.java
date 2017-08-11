package com.bankofamerica.boatestapplication.LocalFulfillment;

import java.net.URISyntaxException;

/**
 * A template for creating new local fulfillment servers
 *
 * Created by TYLER on 7/20/2017.
 */
public class LocalFulfillmentTemplate extends AbstractLocalFulfillment {

    public LocalFulfillmentTemplate() throws URISyntaxException {
        super("ONLINE_FULFILLMENT_URI");
    }

    @Override
    protected String respond(String query) {
        //TODO: place code to process the user's query and return a response
        return null;
    }

}

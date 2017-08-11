package com.bankofamerica.boatestapplication.LocalFulfillment;

import java.net.URISyntaxException;

/**
 * A local fulfillment server that responds to all queries by repeating the original query,
 * which is provided as an example
 *
 * Created by TYLER on 7/20/2017.
 */
public class LocalFulfillmentEcho extends AbstractLocalFulfillment {

    private static final String FULFILLMENT_SERVER_URI = "ws://aog-fulfillment-client-app.herokuapp.com/";

    //Singleton for local fulfillment
    private static ILocalFulfillment instance;
    public static ILocalFulfillment Instance() {
        if (instance == null)
        {
            try {
                instance = new LocalFulfillmentEcho();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    //Instantiate this local fulfillment to correspond with aog-fulfillment-client-app online
    //fulfillment server
    public LocalFulfillmentEcho() throws URISyntaxException {
        super(FULFILLMENT_SERVER_URI);
    }

    @Override
    protected String respond(String query) {
        //The following code echos the user's query back, for demonstration purposes
        return "You said: " + query;
    }

}

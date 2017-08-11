package com.bankofamerica.boatestapplication.Services;

import android.app.IntentService;
import android.content.Intent;

import com.bankofamerica.boatestapplication.AOGAuthentication.AOGAuthentication;
import com.bankofamerica.boatestapplication.R;

/**
 * A service responsible for authenticating the user with login credentials in the background
 *
 * Created by Tyler Collison on 7/10/2017.
 */
public class AOGAuthenticationService extends IntentService {

    public AOGAuthenticationService() {
        super("AOGAuthenticationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Get the conversation ID from data
        String conversationId = intent.getDataString();

        //Get the address of the fulfillment server from strings values
        String fulfillmentServerAddress = this.getApplicationContext().getString(R.string.fulfillment_end_point);

        //Authenticate the conversation
        AOGAuthentication.Instance().authenticateConversation(fulfillmentServerAddress, conversationId);
    }
}

package com.bankofamerica.boatestapplication.Services;

import android.app.IntentService;
import android.content.Intent;

import com.bankofamerica.boatestapplication.LocalFulfillment.LocalFulfillmentEcho;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnCloseEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnCloseEventListener;

/**
 * A service responsible for executing the local fulfillment server in the background
 *
 * Created by Tyler Collison on 7/10/2017.
 */
public class LocalFulfillmentService extends IntentService {

    //Specify the service name
    public LocalFulfillmentService() {
        super("LocalFulfillmentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Get the conversation ID from data
        String conversationId = intent.getDataString();

        //Connect echo fulfillment
        LocalFulfillmentEcho.Instance().connect(conversationId);

        //Add a listener to stop the background service when the connection closes
        final IntentService self = this;
        LocalFulfillmentEcho.Instance().addOnClosedListener(new OnCloseEventListener() {
            @Override
            public void execute(OnCloseEventDelegate message) {
                self.stopSelf();
            }
        });
    }
}

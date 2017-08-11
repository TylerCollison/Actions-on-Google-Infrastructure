package com.bankofamerica.boatestapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bankofamerica.boatestapplication.Services.LocalFulfillmentService;

public class IgnitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the conversation ID from the deep link query string
        Intent intent = getIntent();
        String conversationId = intent.getData().getQueryParameter("conversation_id");

        //Start the local fulfillment service for the supplied conversation ID
        Intent ignitionIntent = new Intent(this, LocalFulfillmentService.class);
        ignitionIntent.setData(Uri.parse(conversationId));
        startService(ignitionIntent);

        //Finish this activity immediately
        finish();
    }
}

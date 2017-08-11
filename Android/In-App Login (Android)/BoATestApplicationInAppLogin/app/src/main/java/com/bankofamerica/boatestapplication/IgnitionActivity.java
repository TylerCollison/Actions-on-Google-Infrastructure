package com.bankofamerica.boatestapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bankofamerica.boatestapplication.Services.AOGAuthenticationService;

public class IgnitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the conversation ID from the deep link query string
        Intent intent = getIntent();
        String conversationId = intent.getData().getQueryParameter(getString(R.string.conversation_id_query_string_name));

        //Start the local fulfillment service for the supplied conversation ID
        Intent ignitionIntent = new Intent(this, AOGAuthenticationService.class);
        ignitionIntent.setData(Uri.parse(conversationId));
        startService(ignitionIntent);

        //Finish this activity immediately
        finish();
    }
}

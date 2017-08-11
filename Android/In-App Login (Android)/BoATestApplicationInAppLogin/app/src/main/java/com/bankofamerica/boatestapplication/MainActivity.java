package com.bankofamerica.boatestapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bankofamerica.boatestapplication.AOGAuthentication.AOGAuthentication;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void authenticate (View v) {
        //Get the user ID from memory
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.settings), Context.MODE_PRIVATE);
        String userId = sharedPref.getString(getString(R.string.googleUserId), "");

        //Get the fulfillment server address from strings values
        String fulfillmentAddress = getString(R.string.fulfillment_end_point);

        //Authenticate the current conversation via the user ID
        AOGAuthentication.Instance().authenticateConversation(fulfillmentAddress, userId);
    }

}
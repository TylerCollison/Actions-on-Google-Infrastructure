package com.bankofamerica.boatestapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GHSetupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghsetup);

        //Get the conversation ID from the deep link query string
        Intent intent = getIntent();
        String userId = intent.getData().getQueryParameter(getString(R.string.user_id_query_string_name));

        //Save the user ID to shared preferences
        SharedPreferences pref = getSharedPreferences(getString(R.string.settings), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(getString(R.string.googleUserId), userId);
        editor.apply();
    }

    public void proceed(View v) {
        //Start the main activity login page
        Intent loginScreenIntent = new Intent(this, MainActivity.class);
        startActivity(loginScreenIntent);
    }
}

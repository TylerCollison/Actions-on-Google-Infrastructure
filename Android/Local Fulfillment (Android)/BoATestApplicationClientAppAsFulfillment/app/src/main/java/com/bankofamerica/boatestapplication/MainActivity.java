package com.bankofamerica.boatestapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bankofamerica.boatestapplication.LocalFulfillment.LocalFulfillmentEcho;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnCloseEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnCloseEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnErrorEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnErrorEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnMessageEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnMessageEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnOpenEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnOpenEventListener;

public class MainActivity extends AppCompatActivity {
    //UI elements
    private TextView consoleTextView;
    private TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect to UI
        consoleTextView = (TextView)findViewById(R.id.consoleTextView);
        registerTextView = (TextView)findViewById(R.id.registerTextView);

        //Add event listeners for message logging
        LocalFulfillmentEcho.Instance().addOnOpenListener(logOpen);
        LocalFulfillmentEcho.Instance().addOnMessageReceivedListener(logMessage);
        LocalFulfillmentEcho.Instance().addOnErrorListener(logError);
        LocalFulfillmentEcho.Instance().addOnClosedListener(logClose);
    }

    /**
     * Register with the Fulfillment Server for the conversation specified by the registerTextView
     * @param v
     */
    public void register(View v) {
        //Connect to server
        LocalFulfillmentEcho.Instance().connect(registerTextView.getText().toString());
    }

    //Listeners for logging to the console
    private OnMessageEventListener logMessage = new OnMessageEventListener() {
        @Override
        public void execute(OnMessageEventDelegate message) {
            log(message.message);
        }
    };
    private OnOpenEventListener logOpen = new OnOpenEventListener() {
        @Override
        public void execute(OnOpenEventDelegate message) {
            log(message.httpStatusMessage);
        }
    };
    private OnErrorEventListener logError = new OnErrorEventListener() {
        @Override
        public void execute(OnErrorEventDelegate message) {
            log(message.exception.getMessage());
        }
    };
    private OnCloseEventListener logClose = new OnCloseEventListener() {
        @Override
        public void execute(OnCloseEventDelegate message) {
            log(message.reason);
        }
    };

    /**
     * Logs the message to the Android Studio and app console
     * @param message Message to be logged
     */
    private void log(final String message) {
        Log.v("Server", message);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                consoleTextView.append(message + "\n");
            }
        });
    }
}

package com.bankofamerica.boatestapplication.LocalFulfillment;

import android.os.AsyncTask;
import android.util.Log;

import com.bankofamerica.boatestapplication.LocalFulfillment.JSON.Query;
import com.bankofamerica.boatestapplication.LocalFulfillment.JSON.RegistrationRequest;
import com.bankofamerica.boatestapplication.LocalFulfillment.JSON.Response;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnCloseEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnCloseEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnErrorEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnMessageEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnMessageEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnOpenEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnOpenEventListener;
import com.bankofamerica.boatestapplication.WebSockets.IWSClient;
import com.bankofamerica.boatestapplication.WebSockets.WSClient;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Provides the core functionality of the local fulfillment server
 *
 * Created by TYLER on 7/20/2017.
 */
public abstract class AbstractLocalFulfillment implements ILocalFulfillment {

    private IWSClient socket;

    public AbstractLocalFulfillment(String onlineFulfillmentUri) throws URISyntaxException {
        //Create a web socket connection to the fulfillment server
        socket = new WSClient(new URI(onlineFulfillmentUri));
    }

    @Override
    public void connect(String conversationId) {
        //Connect to the websocket on a new thread
        new ConnectAsync().execute(conversationId, this);
    }

    @Override
    public void addOnMessageReceivedListener(final OnMessageEventListener listener) {
        socket.addOnMessageReceivedListener(new OnMessageEventListener() {
            @Override
            public void execute(OnMessageEventDelegate message) {
                //Populate a new query object from JSON message
                Query req = new Query(message.message);
                //Call the callback with the query value
                listener.execute(new OnMessageEventDelegate(req.query));
            }
        });
    }

    @Override
    public void addOnOpenListener(OnOpenEventListener listener) {
        socket.addOnOpenListener(listener);
    }

    @Override
    public void addOnClosedListener(OnCloseEventListener listener) {
        socket.addOnClosedListener(listener);
    }

    @Override
    public void addOnErrorListener(OnErrorEventListener listener) {
        socket.addOnErrorListener(listener);
    }

    /**
     * Registers this application with the Fulfillment Server for the specified conversation
     * @param conversationId The conversation ID of the conversation to connect to
     */
    private void register(final String conversationId) {
        this.addOnOpenListener(new OnOpenEventListener() {
            @Override
            public void execute(OnOpenEventDelegate message) {
                //Create registration object
                RegistrationRequest req = new RegistrationRequest(conversationId);
                //Convert registration object to JSON string
                String reqJson = req.toJsonString();
                //Send registration request to server
                socket.send(reqJson);
            }
        });
    }

    /**
     * Sends a response message to the Fulfillment Server for the specified conversation
     * @param conversationId The conversation ID of the conversation to send the message to
     */
    private void setResponse(final String conversationId) {
        this.addOnMessageReceivedListener(new OnMessageEventListener() {
            @Override
            public void execute(OnMessageEventDelegate message) {
                //Get the response for the query
                String response = respond(message.message);
                //Create a new response object
                Response res = new Response(conversationId, response);
                //Convert response object to JSON string
                String resJsonString = res.toJsonString();
                //Send response to server
                socket.send(resJsonString);
            }
        });
    }

    private static class ConnectAsync extends AsyncTask<Object, Object, Object> {
        @Override
        protected Object doInBackground(Object[] objects) {
            //Get the conversation token parameter
            String conversationId = (String)objects[0];
            //Get the local fulfillment parameter
            AbstractLocalFulfillment fulfillment = (AbstractLocalFulfillment) objects[1];
            //Connect to server
            fulfillment.socket.connect();
            //Set message listener for response
            fulfillment.setResponse(conversationId);
            //Register with the online fulfillment server
            fulfillment.register(conversationId);
            return null;
        }
    }

    /**
     * Generates the response that is sent in response to a user query
     * @param query Information regarding the user query
     * @return The response for the user
     */
    protected abstract String respond(String query);
}

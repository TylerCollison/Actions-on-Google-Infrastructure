package com.bankofamerica.boatestapplication.AOGAuthentication;

import android.os.AsyncTask;
import android.util.Log;

import com.bankofamerica.boatestapplication.AOGAuthentication.Events.CheckLoginStatusDelegate;
import com.bankofamerica.boatestapplication.AOGAuthentication.Events.CheckLoginStatusResponseListener;
import com.bankofamerica.boatestapplication.JSON.AuthenticationRequest;
import com.bankofamerica.boatestapplication.JSON.StatusResponse;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Provides functions for user authentication
 *
 * Created by TYLER on 7/27/2017.
 */
public class AOGAuthentication implements IAOGAuthentication {
    //Singleton for authentication
    private static IAOGAuthentication instance;
    public static IAOGAuthentication Instance() {
        if (instance == null)
        {
            instance = new AOGAuthentication();
        }
        return instance;
    }

    @Override
    public void authenticateConversation(String fulfillmentServerAddress, String conversationId) {
        new AsyncClient().execute(fulfillmentServerAddress, conversationId);
    }

    @Override
    public void checkLoginStatus(String fulfillmentServerAddress, String userId, CheckLoginStatusResponseListener listener) {
        new AsyncCheckLoginStatus().execute(fulfillmentServerAddress, userId, listener);
    }

    /**
     * Sends an access token to the server to authenticate the specified conversation
     * @param conversationId The ID associated with the conversation to be authenticated
     */
    private static void authenticate(String fulfillmentServerAddress, String conversationId) throws IOException {
        //Establish the connection with the server
        URL url = new URL(fulfillmentServerAddress + "/authenticate");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        //Generate a new authentication request
        AuthenticationRequest req = new AuthenticationRequest(conversationId);

        //Send the authentication request
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(req.toJsonString());
        outputStream.flush();
        outputStream.close();

        //Read and log the response code
        int responseCode = connection.getResponseCode();
        Log.v("Service", "Status Code: " + String.valueOf(responseCode));

        //Disconnect from the server
        connection.disconnect();
    }

    private static void checkStatus(String fulfillmentServerAddress, String userId, CheckLoginStatusResponseListener listener) throws IOException {
        //Establish the connection with the server
        URL url = new URL(fulfillmentServerAddress + "/status?user_id=" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        //Read and log the response code
        int responseCode = connection.getResponseCode();

        try {
            //Get the response input stream
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            //Form the response into a JSON string
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            //Form the JSON string response into a status response object
            StatusResponse res = new StatusResponse();
            res.populateFromJsonString(builder.toString());
            //Get the status from the response
            String status = res.status;
            //Notify listener
            listener.execute(new CheckLoginStatusDelegate(status));
        } finally {
            //Disconnect from the server
            connection.disconnect();
        }
    }

    private static class AsyncCheckLoginStatus extends AsyncTask<Object, Object, Object> {
        @Override
        protected Object doInBackground(Object[] objects) {
            //Get the parameters for the user ID and listener
            String fulfillmentServerAddress = objects[0].toString();
            String userId = objects[1].toString();
            CheckLoginStatusResponseListener listener = (CheckLoginStatusResponseListener)objects[2];
            //Send authenticate the conversation
            try {
                checkStatus(fulfillmentServerAddress, userId, listener);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Service", e.getMessage());
            }
            return null;
        }
    }

    private static class AsyncClient extends AsyncTask<Object, Object, Object> {
        @Override
        protected Object doInBackground(Object[] objects) {
            //Get the parameters for the server and conversation
            String fulfillmentServerAddress = objects[0].toString();
            String conversationId = objects[1].toString();
            //Send authenticate the conversation
            try {
                authenticate(fulfillmentServerAddress, conversationId);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Service", e.getMessage());
            }
            return null;
        }
    }
}

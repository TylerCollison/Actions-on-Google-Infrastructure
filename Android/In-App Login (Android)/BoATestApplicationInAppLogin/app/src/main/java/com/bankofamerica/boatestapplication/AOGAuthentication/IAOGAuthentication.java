package com.bankofamerica.boatestapplication.AOGAuthentication;

import com.bankofamerica.boatestapplication.AOGAuthentication.Events.CheckLoginStatusResponseListener;

/**
 * Provides functions for user authentication
 *
 * Created by TYLER on 7/27/2017.
 */
public interface IAOGAuthentication {

    /**
     * Authenticates the specified conversation
     * @param conversationId The ID associated with the conversation with which to authenticate
     * @param fulfillmentServerAddress The address of the server at which to send authentication
     */
    void authenticateConversation(String fulfillmentServerAddress, String conversationId);

    /**
     * Checks the login status of the user on the server
     * @param fulfillmentServerAddress The address of the fulfillment server at which to check login status
     * @param userId The ID of the user
     * @param listener Callback listener for response called on network operations complete
     */
    void checkLoginStatus(String fulfillmentServerAddress, String userId, CheckLoginStatusResponseListener listener);

}

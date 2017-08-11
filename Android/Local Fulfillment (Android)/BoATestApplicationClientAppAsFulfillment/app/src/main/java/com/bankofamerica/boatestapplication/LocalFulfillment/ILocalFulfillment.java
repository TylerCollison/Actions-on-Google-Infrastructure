package com.bankofamerica.boatestapplication.LocalFulfillment;

import com.bankofamerica.boatestapplication.WebSockets.Events.OnCloseEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnErrorEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnMessageEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnOpenEventListener;

/**
 * A local fulfillment server responsible for processing and responding to queries forwarded from
 * the online fulfillment server
 *
 * Created by TYLER on 7/20/2017.
 */
public interface ILocalFulfillment {

    /**
     * Connects the local fulfillment server to the online fulfillment server for the specified
     * conversation
     * @param conversationId ID corresponding to the conversation to connect to
     */
    void connect(String conversationId);

    /**
     * Add a listener for message-received events from the Online Fulfillment Server
     * @param listener command object for listener
     */
    void addOnMessageReceivedListener(OnMessageEventListener listener);

    /**
     * Add a listener for the open event when connecting to the Online Fulfillment Server
     * @param listener command object for listener
     */
    void addOnOpenListener(OnOpenEventListener listener);

    /**
     * Add a listener for the closed event when disconnecting from the Online Fulfillment Server
     * @param listener command object for listener
     */
    void addOnClosedListener(OnCloseEventListener listener);

    /**
     * Add a listener for error events from the Online Fulfillment Server
     * @param listener command object for listener
     */
    void addOnErrorListener(OnErrorEventListener listener);

}

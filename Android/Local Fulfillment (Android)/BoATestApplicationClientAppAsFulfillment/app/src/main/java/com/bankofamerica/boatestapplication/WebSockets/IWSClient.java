package com.bankofamerica.boatestapplication.WebSockets;

import com.bankofamerica.boatestapplication.WebSockets.Events.OnCloseEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnErrorEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnMessageEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnOpenEventListener;

/**
 * A websocket client object used to communicate with a server via TCP/TLS
 *
 * Created by TYLER on 7/20/2017.
 */
public interface IWSClient {

    /**
     * Add listener for messages received through the websocket
     * @param listener Message listener
     */
    void addOnMessageReceivedListener(OnMessageEventListener listener);

    /**
     * Add listener for the websocket's open event
     * @param listener Open event listener
     */
    void addOnOpenListener(OnOpenEventListener listener);

    /**
     * Add listener for the websocket's close event
     * @param listener Close event listener
     */
    void addOnClosedListener(OnCloseEventListener listener);

    /**
     * Add listener for the websocket's error events
     * @param listener Error event listener
     */
    void addOnErrorListener(OnErrorEventListener listener);

    /**
     * Connect the websocket
     */
    void connect();

    /**
     * Disconnect the websocket
     */
    void disconnect();

    /**
     * Send a message over the websocket
     */
    void send(String message);
}

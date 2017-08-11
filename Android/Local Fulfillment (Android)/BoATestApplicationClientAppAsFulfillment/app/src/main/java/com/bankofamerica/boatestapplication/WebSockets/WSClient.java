package com.bankofamerica.boatestapplication.WebSockets;

import com.bankofamerica.boatestapplication.WebSockets.Events.OnCloseEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnCloseEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnErrorEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnErrorEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnMessageEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnMessageEventListener;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnOpenEventDelegate;
import com.bankofamerica.boatestapplication.WebSockets.Events.OnOpenEventListener;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Wraps the JavaWebSocketsClient library to create a reusable TCP websocket object
 *
 * Created by Tyler Collison on 7/10/2017.
 */
public class WSClient implements IWSClient {
    //Store the main event listeners
    private List<OnMessageEventListener> onMessageEventListeners = new ArrayList<>();
    private List<OnOpenEventListener> onOpenListeners = new ArrayList<>();
    private List<OnCloseEventListener> onClosedListeners = new ArrayList<>();
    private List<OnErrorEventListener> onErrorListeners = new ArrayList<>();

    //The actual websocket
    private JavaWebSocketsClient jwsClient;
    //The URI that the websocket is connected to
    private URI serverUri;

    public WSClient(URI serverUri) {
        jwsClient = new JavaWebSocketsClient(serverUri);
        //Store the URI of the websocket
        this.serverUri = serverUri;
    }

    @Override
    public void addOnMessageReceivedListener(OnMessageEventListener listener) {
        onMessageEventListeners.add(listener);
    }

    @Override
    public void addOnOpenListener(OnOpenEventListener listener) {
        onOpenListeners.add(listener);
    }

    @Override
    public void addOnClosedListener(OnCloseEventListener listener) {
        onClosedListeners.add(listener);
    }

    @Override
    public void addOnErrorListener(OnErrorEventListener listener) {
        onErrorListeners.add(listener);
    }

    @Override
    public void connect() {
        //Determine whether the current websocket has been used
        if (jwsClient.isUsed) {
            //Reset the websocket
            reset();
        }
        //Connect to the websocket
        jwsClient.connect();
    }

    @Override
    public void disconnect() {
        jwsClient.close();
    }

    @Override
    public void send(String message) {
        jwsClient.send(message);
    }

    private void reset() {
        //Disconnect from the current session
        disconnect();
        //Clear all listeners
        onMessageEventListeners.clear();
        onOpenListeners.clear();
        onErrorListeners.clear();
        //Instantiate a new session
        jwsClient = new JavaWebSocketsClient(serverUri);
    }

    private class JavaWebSocketsClient extends WebSocketClient {

        boolean isUsed = false;

        JavaWebSocketsClient(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            for (OnOpenEventListener listener : onOpenListeners) {
                listener.execute(new OnOpenEventDelegate(handshakedata.getHttpStatus(),
                        handshakedata.getHttpStatusMessage()));
            }
            isUsed = true;
        }

        @Override
        public void onMessage(String message) {
            for (OnMessageEventListener listener : onMessageEventListeners) {
                listener.execute(new OnMessageEventDelegate(message));
            }
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            //Notify all listeners
            for (OnCloseEventListener listener : onClosedListeners) {
                listener.execute(new OnCloseEventDelegate(code, reason));
            }
            //Clear all listeners
            onClosedListeners.clear();
        }

        @Override
        public void onError(Exception ex) {
            for (OnErrorEventListener listener : onErrorListeners) {
                listener.execute(new OnErrorEventDelegate(ex));
            }
        }
    }
}

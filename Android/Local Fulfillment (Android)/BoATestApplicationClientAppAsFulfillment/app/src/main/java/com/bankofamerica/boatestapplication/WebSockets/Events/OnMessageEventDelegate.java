package com.bankofamerica.boatestapplication.WebSockets.Events;

/**
 * An event delegate for the OnMessageEventListener
 *
 * Created by TYLER on 7/20/2017.
 */
public class OnMessageEventDelegate {
    public String message;

    public OnMessageEventDelegate(String message) {
        this.message = message;
    }
}

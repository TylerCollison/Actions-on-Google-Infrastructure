package com.bankofamerica.boatestapplication.WebSockets.Events;

/**
 * An event delegate for the OnErrorEventListener
 *
 * Created by TYLER on 7/20/2017.
 */
public class OnErrorEventDelegate {
    public Exception exception;

    public OnErrorEventDelegate(Exception exception) {
        this.exception = exception;
    }
}

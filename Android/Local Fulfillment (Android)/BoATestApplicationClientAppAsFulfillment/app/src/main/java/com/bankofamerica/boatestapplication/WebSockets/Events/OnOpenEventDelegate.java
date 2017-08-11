package com.bankofamerica.boatestapplication.WebSockets.Events;

/**
 * An event delegate for the OnOpenEventListener
 *
 * Created by TYLER on 7/20/2017.
 */
public class OnOpenEventDelegate {
    public int httpStatus;
    public String httpStatusMessage;

    public OnOpenEventDelegate(int httpStatus, String httpStatusMessage) {
        this.httpStatus = httpStatus;
        this.httpStatusMessage = httpStatusMessage;
    }
}

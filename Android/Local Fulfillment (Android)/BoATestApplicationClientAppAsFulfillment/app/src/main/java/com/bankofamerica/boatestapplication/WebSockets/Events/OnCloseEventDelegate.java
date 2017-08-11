package com.bankofamerica.boatestapplication.WebSockets.Events;

/**
 * Event delegate for the OnCloseEventListener
 *
 * Created by TYLER on 7/20/2017.
 */
public class OnCloseEventDelegate {
    public int code;
    public String reason;

    public OnCloseEventDelegate(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }
}

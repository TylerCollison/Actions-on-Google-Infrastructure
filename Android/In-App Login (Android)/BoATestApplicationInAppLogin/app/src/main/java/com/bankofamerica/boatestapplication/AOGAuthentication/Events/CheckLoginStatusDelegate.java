package com.bankofamerica.boatestapplication.AOGAuthentication.Events;

/**
 * Event delegate for the CheckLoginStatusResponseListener
 *
 * Created by TYLER on 8/2/2017.
 */
public class CheckLoginStatusDelegate {
    public String status;

    public CheckLoginStatusDelegate(String status) {
        this.status = status;
    }
}

package com.bankofamerica.boatestapplication.AOGAuthentication.Events;

/**
 * Event listener executed upon receiving a login status response
 *
 * Created by TYLER on 8/2/2017.
 */
public abstract class CheckLoginStatusResponseListener {
    public abstract void execute(CheckLoginStatusDelegate data);
}

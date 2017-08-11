package com.bankofamerica.boatestapplication.WebSockets.Events;

/**
 * A listener executed on connection error
 *
 * Created by TYLER on 7/20/2017.
 */
public abstract class OnErrorEventListener {

    /**
     * Executed when the event occurs
     * @param message Message received from the event
     */
    public abstract void execute(OnErrorEventDelegate message);

}

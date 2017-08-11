package com.bankofamerica.boatestapplication.WebSockets.Events;

/**
 * A listener executed on connection open event
 *
 * Created by TYLER on 7/20/2017.
 */
public abstract class OnOpenEventListener {

    /**
     * Executed when the event occurs
     * @param message Message received from the event
     */
    public abstract void execute(OnOpenEventDelegate message);

}

package com.bankofamerica.boatestapplication.WebSockets.Events;

/**
 * A listener executed on a connection close event
 *
 * Created by TYLER on 7/20/2017.
 */
public abstract class OnCloseEventListener {

    /**
     * Executed when the event occurs
     * @param message Message received from the event
     */
    public abstract void execute(OnCloseEventDelegate message);

}

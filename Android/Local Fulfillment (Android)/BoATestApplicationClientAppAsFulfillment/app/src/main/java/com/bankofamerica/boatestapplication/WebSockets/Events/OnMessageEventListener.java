package com.bankofamerica.boatestapplication.WebSockets.Events;

/**
 * A listener executed on connection message received event
 *
 * Created by Tyler Collison on 7/10/2017.
 */
public abstract class OnMessageEventListener {

    /**
     * Executed when the event occurs
     * @param message Message received from the event
     */
    public abstract void execute(OnMessageEventDelegate message);

}

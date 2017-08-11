package com.bankofamerica.boatestapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * An alarm that broadcasts pending intents lazily on an interval in the background
 *
 * Created by TYLER on 8/4/2017.
 */
public class BackgroundAlarm
{
    //Store all pending intents to broadcast on the interval
    private List<PendingIntent> pendingIntents = new ArrayList<>();

    /**
     * Adds a pending intent to be broadcast on each interval of the alarm
     * @param pendingIntent Pending intent to be added
     */
    public void addOnTickPendingIntent(PendingIntent pendingIntent) {
        pendingIntents.add(pendingIntent);
    }


    /**
     * Starts the alarm with the specified time interval between broadcasts
     * @param context The current context
     * @param interval Approximate period of time between broadcasts in milliseconds
     * @param pendingIntent Pending intent to broadcast
     */
    public static void startAlarm(Context context, int interval, PendingIntent pendingIntent)
    {
        //Create a calendar to set the time of the alarm start
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, interval);

        // Start the alarm; RTC does not wake the device up
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), interval, pendingIntent);
    }


    /**
     * Stops the alarm and the broadcast for the given pending intent
     * @param context The current context
     * @param pendingIntent The pending intent to stop
     */
    public static void stopAlarm(Context context, PendingIntent pendingIntent)
    {
        //Stop the alarm
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}
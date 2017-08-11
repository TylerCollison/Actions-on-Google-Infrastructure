package com.bankofamerica.boatestapplication.LoginWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.widget.RemoteViews;

import com.bankofamerica.boatestapplication.AOGAuthentication.AOGAuthentication;
import com.bankofamerica.boatestapplication.AOGAuthentication.Events.CheckLoginStatusDelegate;
import com.bankofamerica.boatestapplication.AOGAuthentication.Events.CheckLoginStatusResponseListener;
import com.bankofamerica.boatestapplication.BackgroundAlarm;
import com.bankofamerica.boatestapplication.R;
import com.bankofamerica.boatestapplication.Services.AOGAuthenticationService;

/**
 * Implementation of App Widget functionality.
 */
public class GHLoginWidget extends AppWidgetProvider {
    //The approximate time interval between widget updates
    private static final int UPDATE_INTERVAL = 90000;
    //The number of milliseconds delayed before checking login status after login
    private static final int LOGIN_DELAY = 1000;

    static void updateAppWidget(Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {
        // Construct the RemoteViews object
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ghlogin_widget);

        //Get strings values
        String loginButtonClick = context.getString(R.string.login_button_click_intent);
        String fulfillmentServerAddress = context.getString(R.string.fulfillment_end_point);

        //Load the user ID
        String userId = getUserIdFromMemory(context);

        //Set the listener for the login button click
        Intent onClickIntent = new Intent(loginButtonClick);
        PendingIntent onClickPendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), appWidgetId, onClickIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        //Apply the pending intent to the button
        views.setOnClickPendingIntent(R.id.loginButton, onClickPendingIntent);

        //Update the UI to reflect the user's current login status
        AOGAuthentication.Instance().checkLoginStatus(fulfillmentServerAddress, userId, new CheckLoginStatusResponseListener() {
            @Override
            public void execute(final CheckLoginStatusDelegate data) {
                //Update the status indicator with the current status
                views.setTextViewText(R.id.statusLabel, data.status);

                // Instruct the widget manager to update the widget
                appWidgetManager.updateAppWidget(appWidgetId, views);
            }
        });

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        super.onReceive(context, intent);

        //Get strings values
        String loginButtonClick = context.getString(R.string.login_button_click_intent);
        String actionAutoUpdate = context.getString(R.string.action_auto_update_intent);

        if (intent.getAction().equals(loginButtonClick)) {
            //Handle the login button click event
            onLoginButtonClick(context);
        }

        if(intent.getAction().equals(actionAutoUpdate))
        {
            //Update all of the widgets
            updateAllWidgets(context);
        }
    }

    @Override
    public void onEnabled(Context context)
    {
        //Get the auto-update pending intent
        PendingIntent autoUpdatePendingIntent = generateAutoUpdatePendingIntent(context);

        //Start the alarm
        BackgroundAlarm.startAlarm(context, UPDATE_INTERVAL, autoUpdatePendingIntent);
    }

    @Override
    public void onDisabled(Context context)
    {
        //Get the auto-update pending intent
        PendingIntent autoUpdatePendingIntent = generateAutoUpdatePendingIntent(context);

        //Stop the alarm
        BackgroundAlarm.stopAlarm(context, autoUpdatePendingIntent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private PendingIntent generateAutoUpdatePendingIntent(Context context) {
        //Get strings values
        String actionAutoUpdate = context.getString(R.string.action_auto_update_intent);

        //Setup the auto-update broadcast intent
        Intent autoUpdateIntent = new Intent(actionAutoUpdate);
        return PendingIntent.getBroadcast(context, 0, autoUpdateIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }


    /**
     * Handles logic for the login button click event
     * @param context The current context
     */
    private void onLoginButtonClick(final Context context) {
        //Load the user ID
        String userId = getUserIdFromMemory(context);

        //Authenticate the user
        authenticateInBackground(context, userId);

        //Wait to allow the login status to change on the network
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //Update all of the widgets
                updateAllWidgets(context);
            }
        }, LOGIN_DELAY);
    }

    /**
     * Updates all of the widgets of the specified context
     * @param context Context the widgets belong to
     */
    private void updateAllWidgets(Context context) {
        //Get the widget manager and IDs associated with this widget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisAppWidget = new ComponentName(context.getPackageName(), GHLoginWidget.class.getName());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

        //Update all of the widgets
        onUpdate(context, appWidgetManager, appWidgetIds);
    }

    /**
     * Retrieves the User ID from shared preferences
     * @param context The current context
     * @return The user's Actions on Google ID
     */
    private static String getUserIdFromMemory(Context context) {
        String userId = "";

        //Get preference strings
        String settings = context.getString(R.string.settings);
        String googleUserId = context.getString(R.string.googleUserId);

        //Get the user id from preferences
        SharedPreferences sharedPref = context.getSharedPreferences(settings, Context.MODE_PRIVATE);
        userId = sharedPref.getString(googleUserId, "");

        return userId;
    }

    /**
     * Authenticates the user with the Fulfillment server in the background
     * @param context The current context
     * @param userId The user's Actions on Google ID
     */
    private void authenticateInBackground(Context context, String userId) {
        //Setup the authentication background service
        Intent serviceIntent = new Intent(context, AOGAuthenticationService.class);
        serviceIntent.setData(Uri.parse(userId));
        context.startService(serviceIntent);
    }


}


package com.sigma.prouds.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.sigma.prouds.PagerActivity;
import com.sigma.prouds.R;
import com.sigma.prouds.SplashActivity;
import com.sigma.prouds.util.NotificationHelper;

/**
 * Created by lucgu.qolfiera on 9/24/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//Intent to invoke app when click on notification.
        //In this sample, we want to start/launch this sample app when user clicks on notification
        Log.i("aaaa","bbb");

        MyWakeLock.acquire(context);
        Intent intentToRepeat = new Intent(context, SplashActivity.class);
        //set flag to restart/relaunch the app
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Pending intent to handle launch of Activity in intent above
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, NotificationHelper.ALARM_TYPE_RTC, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);

        //Build notification
        Notification repeatedNotification = buildLocalNotification(context, pendingIntent).build();

        //Send local notification
        NotificationHelper.getNotificationManager(context).notify(NotificationHelper.ALARM_TYPE_RTC, repeatedNotification);
    }

    public NotificationCompat.Builder buildLocalNotification(Context context, PendingIntent pendingIntent) {
        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("Timesheet reminder")
                        .setContentText("Dont forget to add timesheet for today")
                        .setAutoCancel(true);


        return builder;
    }
}

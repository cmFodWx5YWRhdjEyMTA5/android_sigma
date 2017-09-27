package com.sigma.prouds.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.sigma.prouds.R;
import com.sigma.prouds.SplashActivity;
import com.sigma.prouds.util.NotificationHelper;

/**
 * Created by lucgu.qolfiera on 9/27/2017.
 */

public class NotificationService extends Service
{

    /*@Override
    public void onCreate()
    {
        super.onCreate();
        NotificationHelper.scheduleRepeatingRTCNotification(this, null, null);
        NotificationHelper.enableBootReceiver(this);

    }*/

    @Override
    public int onStartCommand(Intent intent, int flags,  int startId) {


        /*** Put your code here ***/
        Log.i("Service ", "started");
        NotificationHelper.scheduleRepeatingRTCNotification(this, null, null);
        NotificationHelper.enableBootReceiver(this);
        //startForeground(1, new Notification());
        return Service.START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public NotificationService getService() {
            return NotificationService.this;
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i("Service ", "stopped");
        startService(new Intent(getApplicationContext(), NotificationService.class));

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
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
        };

        registerReceiver(broadcastReceiver, new IntentFilter());
        NotificationHelper.scheduleRepeatingRTCNotification(this, null, null);
        NotificationHelper.enableBootReceiver(this);
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

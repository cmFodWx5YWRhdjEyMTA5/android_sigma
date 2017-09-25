package com.sigma.prouds.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sigma.prouds.util.NotificationHelper;

/**
 * Created by ptyagi on 4/18/17.
 */

public class AlarmBootReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            //only enabling one type of notifications for demo purposes
            NotificationHelper.scheduleRepeatingElapsedNotification(context);
        }
    }
}
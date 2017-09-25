package com.sigma.prouds.service;

/**
 * Created by lucgu.qolfiera on 9/24/2017.
 */

import android.content.Context;
import android.os.PowerManager;

import com.sigma.prouds.PagerActivity;

public abstract class MyWakeLock {
    private static PowerManager.WakeLock wakeLock;

    public static void acquire(Context c) {
        if (wakeLock != null) wakeLock.release();

        PowerManager pm = (PowerManager) c.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.ON_AFTER_RELEASE, "Main");
        wakeLock.acquire();
    }

    public static void release() {
        if (wakeLock != null){
            wakeLock.release();
        }
        wakeLock = null;
    }
}
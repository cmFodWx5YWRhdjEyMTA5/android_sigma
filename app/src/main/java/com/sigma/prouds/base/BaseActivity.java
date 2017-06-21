package com.sigma.prouds.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.androidquery.AQuery;

import de.greenrobot.event.EventBus;

/**
 * Created by 1414 on 6/21/2017.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    protected AQuery query;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        query = new AQuery(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onPause()
    {
        if (EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().unregister(this);
        }

        super.onPause();
    }

    public void onEvent(Void v)
    {
        Log.i(getClass().getSimpleName(), "Empty event");
    }

    protected abstract int getLayout();

    protected abstract void workingSpace();
}

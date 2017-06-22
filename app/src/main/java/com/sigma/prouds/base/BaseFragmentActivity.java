package com.sigma.prouds.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.androidquery.AQuery;

import de.greenrobot.event.EventBus;

/**
 * Created by 1414 on 6/22/2017.
 */

public abstract class BaseFragmentActivity extends FragmentActivity
{
    protected AQuery query;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        query = new AQuery(this);
        workingSpace();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onPause()
    {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    public void onEvent(Void v)
    {
        Log.i(getClass().getSimpleName(), "Empty event");
    }

    protected abstract int getLayout();

    protected abstract void workingSpace();
}

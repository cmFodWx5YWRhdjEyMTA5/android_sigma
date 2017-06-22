package com.sigma.prouds.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;

import de.greenrobot.event.EventBus;

/**
 * Created by 1414 on 6/22/2017.
 */

public abstract class BaseFragment extends Fragment
{
    protected AQuery query;
    protected View viewRoot;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        viewRoot = inflater.inflate(getLayout(), container, false);
        query = new AQuery(getActivity(), viewRoot);
        workingSpace(viewRoot);
        return viewRoot;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop()
    {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroy()
    {
        query = null;
        super.onDestroy();
    }

    protected abstract int getLayout();

    public void onEvent(Void v)
    {
        Log.i(getClass().getSimpleName(), "Empty event");
    }

    protected abstract void workingSpace(View view);
}

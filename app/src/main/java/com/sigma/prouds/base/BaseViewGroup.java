package com.sigma.prouds.base;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.androidquery.AQuery;

/**
 * Created by 1414 on 6/22/2017.
 */

public abstract class BaseViewGroup extends LinearLayout
{
    protected Application app;
    protected AQuery query;

    private boolean viewAvailable;

    public BaseViewGroup(Context context)
    {
        super(context);
        app = (Application) context.getApplicationContext();
        inflate(context);
        setId((int) (System.currentTimeMillis() / 1000));

        workingSpace();
        viewAvailable = true;
    }

    public int getXML()
    {
        return getLayout();
    }

    private void inflate(Context context)
    {
        View view = inflate(context, getLayout(), this);
        query = new AQuery(view);
    }

    public void manualDetach()
    {
        onDetachedFromWindow();
    }

    @Override
    protected void onDetachedFromWindow()
    {
        viewAvailable = false;
        if (query != null)
        {
            query = null;
        }

        app = null;

        super.onDetachedFromWindow();
    }

    public boolean isViewAvailable()
    {
        return viewAvailable;
    }

    protected abstract int getLayout();

    protected abstract void workingSpace();
}

package com.sigma.prouds.fragment;

import android.content.Context;
import android.view.View;

import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseFragment;

/**
 * Created by lucgu.qolfiera on 8/13/2017.
 */

public class ChartFragment extends BaseFragment
{
    static Context ctx;

    public static ChartFragment newInstance(Context context)
    {
        ChartFragment fragment = new ChartFragment();
        ctx = context;
        return fragment;
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_chart;
    }

    @Override
    protected void workingSpace(View view)
    {

    }
}

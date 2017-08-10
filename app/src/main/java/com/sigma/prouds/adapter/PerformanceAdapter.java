package com.sigma.prouds.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sigma.prouds.fragment.MyActivityFragment;

/**
 * Created by 1414 on 8/8/2017.
 */

public class PerformanceAdapter extends FragmentPagerAdapter
{
    private Context context;

    public PerformanceAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return MyActivityFragment.newInstance(context);
            default:return MyActivityFragment.newInstance(context);
        }
    }

    @Override
    public int getCount()
    {
        return 2;
    }
}

package com.sigma.prouds.fragment;

import android.content.Context;
import android.view.View;

import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseFragment;

/**
 * Created by goy on 7/15/2017.
 */

public class WorkplanFragment extends BaseFragment {

    static Context ctx;

    public static WorkplanFragment newInstance(Context context)
    {
        WorkplanFragment fragment = new WorkplanFragment();
        ctx = context;
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_workplan;
    }

    @Override
    protected void workingSpace(View view) {

    }
}

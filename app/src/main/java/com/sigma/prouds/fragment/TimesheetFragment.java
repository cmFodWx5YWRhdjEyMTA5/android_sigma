package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseFragment;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;


/**
 * Created by goy on 7/7/2017.
 */

public class TimesheetFragment extends BaseFragment {
    static Context ctx;

    public static TimesheetFragment newInstance(Context context) {
        TimesheetFragment fragment = new TimesheetFragment();
        ctx = context;
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_timesheet;
    }

    @Override
    protected void workingSpace(View view) {
        assignXML();

    }

    public void assignXML() {
    }


}

package com.sigma.prouds.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sigma.prouds.AddTimesheetActivity;
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
    private CalendarView cvDate;
    private TextView tvDate;
    private TextView tvHour;
    private LinearLayout llAddTimesheet;

    String[] sampleDate = {"Wed, Jun 7", "Wed, Jun 8", "Wed, Jun 9", "Wed, Jun 10"};
    String[] sampleHour = {"3", "4", "5", "6"};

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
        llAddTimesheet = (LinearLayout) view.findViewById(R.id.ll_add_timesheet);
        llAddTimesheet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toAddTimesheet();
            }
        });

//        carouselSetup();
    }

    public void assignXML() {
        cvDate = (CalendarView) viewRoot.findViewById(R.id.cv_timesheet);
    }

    /*public void carouselSetup() {
        cvDate.setPageCount(sampleDate.length);
        cvDate.setSlideInterval(1000);
        cvDate.setViewListener(viewListener);
    }*/


    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {

            View cvContent = getActivity().getLayoutInflater().inflate(R.layout.carousel, null);
            tvDate = (TextView) cvContent.findViewById(R.id.tv_ts_date);
            tvHour = (TextView) cvContent.findViewById(R.id.tv_ts_hour);

            tvDate.setText(sampleDate[position]);
            tvHour.setText(sampleHour[position]);

            //cvDate.setIndicatorGravity(Gravity.CENTER_HORIZONTAL| Gravity.BOTTOM);

            return cvContent;
        }
    };

    public void toAddTimesheet()
    {
        Intent intent = new Intent(getActivity(), AddTimesheetActivity.class);
        startActivity(intent);
    }

}

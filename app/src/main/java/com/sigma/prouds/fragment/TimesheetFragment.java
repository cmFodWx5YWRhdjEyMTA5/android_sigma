package com.sigma.prouds.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sigma.prouds.AddTimesheetActivity;
import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseFragment;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private LinearLayout llAddTimesheet, llChooseDate;



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
        tvDate = (TextView) view.findViewById(R.id.tv_ts_date);

        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "EEE, MMM d yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                tvDate.setText(sdf.format(calendar.getTime()));
            }
        };
        llChooseDate = (LinearLayout) view.findViewById(R.id.ll_ts_choose_date);
        llChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        llAddTimesheet = (LinearLayout) view.findViewById(R.id.ll_add_timesheet);
        llAddTimesheet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toAddTimesheet();
            }
        });
    }

    public void toAddTimesheet()
    {
        Intent intent = new Intent(getActivity(), AddTimesheetActivity.class);
        startActivity(intent);
    }

}

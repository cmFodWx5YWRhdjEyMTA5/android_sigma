package com.sigma.prouds.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sigma.prouds.AddTimesheetActivity;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.TimesheetAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.model.ProjectListTimesheetSenderModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.UserProjectTimesheetResponse;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import in.goodiebag.carouselpicker.CarouselPicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by goy on 7/7/2017.
 */

public class TimesheetFragment extends BaseFragment {
    static Context ctx;
    private CalendarView cvDate;
    private TextView tvDate, tvHour, tvEmpty, tvChooseDate, tvNewTimesheet, tvDateBelow;
    private LinearLayout  llChooseDate;
    private RelativeLayout llAddTimesheet;
    private ProgressBar pbTimeSheet;
    private ApiService service;
    private ProudsApplication app;
    private TimesheetAdapter adapter;
    private RecyclerView rvTimesheet;
    private LinearLayout llDayOff;

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
        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();
        llDayOff = (LinearLayout) view.findViewById(R.id.ll_ts_dayoff);
        rvTimesheet = (RecyclerView) view.findViewById(R.id.rv_timesheet);
        pbTimeSheet = (ProgressBar) view.findViewById(R.id.pb_timesheet);
        tvDate = (TextView) view.findViewById(R.id.tv_ts_date);
        tvHour = (TextView) view.findViewById(R.id.tv_ts_hour);
        tvChooseDate = (TextView) view.findViewById(R.id.tv_ts_choose_date);
        tvNewTimesheet = (TextView) view.findViewById(R.id.tv_ts_new);
        tvDateBelow = (TextView) view.findViewById(R.id.tv_ts_date_below);
        tvEmpty = (TextView) view.findViewById(R.id.tv_empty_timesheet);

        setTypeFace();

        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String fullFormat = "EEE, MMM d yyyy";
                String halfFormat = "EEE, MMM d";
                String dataFormat = "yyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(fullFormat);
                SimpleDateFormat sdfHalf = new SimpleDateFormat(halfFormat);
                SimpleDateFormat sdfData = new SimpleDateFormat(dataFormat);
                String form = year + "-" + String.valueOf(month + 1)+ "-" + dayOfMonth;
                tvDate.setText(sdf.format(calendar.getTime()));
                tvDateBelow.setText(sdfHalf.format(calendar.getTime()).toUpperCase());
                Log.i("Date selected : ", sdfData.format(calendar.getTime()));
                getData(sdfData.format(calendar.getTime()));

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

        llAddTimesheet = (RelativeLayout) view.findViewById(R.id.ll_add_timesheet);
        llAddTimesheet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toAddTimesheet();
            }
        });

        String fullFormat = "EEE, MMM d yyyy";
        String halfFormat = "EEE, MMM d";
        SimpleDateFormat sdfFull = new SimpleDateFormat(fullFormat);
        SimpleDateFormat sdfHalf = new SimpleDateFormat(halfFormat);
        tvDate.setText(sdfFull.format(calendar.getTime()));
        tvDateBelow.setText(sdfHalf.format(calendar.getTime()).toUpperCase());
        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
        Log.i("Date today : ", fDate);
        getData(fDate);
    }

    private void setTypeFace() {
        query.id(R.id.tv_ts_date).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_ts_hour).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_ts_choose_date).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_ts_new).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_ts_date_below).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_empty_timesheet).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
    }

    public void toAddTimesheet()
    {
        Intent intent = new Intent(getActivity(), AddTimesheetActivity.class);
        startActivity(intent);
    }

    public void getData(final String date)
    {
        rvTimesheet.setVisibility(View.INVISIBLE);
        pbTimeSheet.setVisibility(View.VISIBLE);
        tvEmpty.setVisibility(View.GONE);
        ProjectListTimesheetSenderModel model = new ProjectListTimesheetSenderModel();
        model.setMobile("1");
        model.setDate(date);
        service.getUserProjectTimesheet(app.getSessionManager().getToken(), model).enqueue(new Callback<UserProjectTimesheetResponse>() {
            @Override
            public void onResponse(Call<UserProjectTimesheetResponse> call, Response<UserProjectTimesheetResponse> response)
            {
                if (response.body().getUserActivities().size() == 0)
                {
                    tvEmpty.setVisibility(View.VISIBLE);
                }
                rvTimesheet.setVisibility(View.VISIBLE);
                pbTimeSheet.setVisibility(View.GONE);
                Log.i("Succes : ", response.body().getUserActivities().size() + "");
                adapter = new TimesheetAdapter(ctx, response.body().getUserActivities());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
                rvTimesheet.setLayoutManager(mLayoutManager);
                rvTimesheet.setAdapter(adapter);
                /*int hour = 0;
                for (int i=0; i <= response.body().getUserActivities().size()-1;i++)
                {
                    hour += Integer.parseInt(response.body().getUserActivities().get(i).getHourTotal());
                }
                tvHour.setText(hour + "Hours");*/

                // SET DAYOFF LAYOUT
                for (int i = 0; i < response.body().getHolidays().size()-1; i++) {
                    Log.i("holiday",response.body().getHolidays().get(i).getHolidayDate().equals(date)+"");
                    if (response.body().getHolidays().get(i).getHolidayDate().equals(date)) {
                        llDayOff.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProjectTimesheetResponse> call, Throwable t)
            {

            }
        });
    }



}

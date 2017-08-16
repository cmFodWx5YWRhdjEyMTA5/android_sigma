package com.sigma.prouds.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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
    private TextView tvDate;
    private TextView tvHour;
    private LinearLayout llAddTimesheet, llChooseDate;

    private ApiService service;
    private ProudsApplication app;
    private TimesheetAdapter adapter;
    private RecyclerView rvTimesheet;

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
        rvTimesheet = (RecyclerView) view.findViewById(R.id.rv_timesheet);
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
                String form = year + "-" + String.valueOf(month + 1)+ "-" + dayOfMonth;
                tvDate.setText(sdf.format(calendar.getTime()));
                Log.i("Date selected : ", form);
                getData(form);

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

        String myFormat = "EEE, MMM d yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        tvDate.setText(sdf.format(calendar.getTime()));
        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
        Log.i("Date today : ", fDate);
        getData(fDate);
    }

    public void toAddTimesheet()
    {
        Intent intent = new Intent(getActivity(), AddTimesheetActivity.class);
        startActivity(intent);
    }

    public void getData(String date)
    {
        ProjectListTimesheetSenderModel model = new ProjectListTimesheetSenderModel();
        model.setMobile("1");
        model.setDate(date);
        service.getUserProjectTimesheet(app.getSessionManager().getToken(), model).enqueue(new Callback<UserProjectTimesheetResponse>() {
            @Override
            public void onResponse(Call<UserProjectTimesheetResponse> call, Response<UserProjectTimesheetResponse> response)
            {
                Log.i("Succes : ", response.body().getUserActivities().size() + "");
                adapter = new TimesheetAdapter(ctx, response.body().getUserActivities());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
                rvTimesheet.setLayoutManager(mLayoutManager);
                rvTimesheet.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UserProjectTimesheetResponse> call, Throwable t)
            {

            }
        });
    }



}

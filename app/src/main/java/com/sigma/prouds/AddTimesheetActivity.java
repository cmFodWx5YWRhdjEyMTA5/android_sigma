package com.sigma.prouds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;

import java.util.Calendar;
import java.util.Date;

public class AddTimesheetActivity extends BaseActivity {

    private ProudsApplication app;
    private ApiService service;
    private EditText etDate, etProject, etTask, etWorkHour, etSubject, etMessage;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_add_timesheet;
    }

    @Override
    protected void workingSpace()
    {
        app = (ProudsApplication) getApplicationContext();
        service = ApiUtils.apiService();
        assignXML();
        getTodayDate();
    }

    public void assignXML()
    {
        etDate = (EditText) findViewById(R.id.et_addts_date);
        etProject = (EditText) findViewById(R.id.et_addts_project);
        etTask = (EditText) findViewById(R.id.et_addts_task);
        etWorkHour = (EditText) findViewById(R.id.et_addts_hours);
        etSubject = (EditText) findViewById(R.id.et_addts_subject);
        etMessage = (EditText) findViewById(R.id.et_addts_msg);
    }

    public void getTodayDate()
    {
        Date currentTime = Calendar.getInstance().getTime();
        etDate.setText(currentTime.toString());
    }

}

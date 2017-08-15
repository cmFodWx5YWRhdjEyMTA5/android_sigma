package com.sigma.prouds;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.AddTimeSheetModel;
import com.sigma.prouds.model.ProjectListTimesheetSenderModel;
import com.sigma.prouds.model.TaskListTimesheetSenderModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.AddTimeSheetResponse;
import com.sigma.prouds.network.response.TaskAddTimeSheetResponse;
import com.sigma.prouds.network.response.UserProjectTimesheetResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTimesheetActivity extends BaseActivity {

    private ProudsApplication app;
    private ApiService service;
    private ProjectListTimesheetSenderModel model;
    private EditText etDate, etProject, etTask, etWorkHour, etSubject, etMessage;
    private RelativeLayout rlAdd;
    private String projectId;
    private String wpId;
    private ProgressDialog dialog;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_add_timesheet;
    }

    @Override
    protected void workingSpace()
    {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait");
        app = (ProudsApplication) getApplicationContext();
        service = ApiUtils.apiService();
        assignXML();
        model = new ProjectListTimesheetSenderModel();
        getTodayDate();
        etProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProjectData();
            }
        });
        etTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTaskList();
            }
        });

        rlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (etProject.getText().length() == 0 && etTask.getText().length() == 0)
                {
                    Toast.makeText(AddTimesheetActivity.this, "Form must be filled", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    addTimeSheet();
                }
            }
        });
    }

    public void assignXML()
    {
        etDate = (EditText) findViewById(R.id.et_addts_date);
        etProject = (EditText) findViewById(R.id.et_addts_project);
        etTask = (EditText) findViewById(R.id.et_addts_task);
        etWorkHour = (EditText) findViewById(R.id.et_addts_hours);
        etSubject = (EditText) findViewById(R.id.et_addts_subject);
        etMessage = (EditText) findViewById(R.id.et_addts_msg);
        rlAdd = (RelativeLayout) findViewById(R.id.rl_addts);

    }

    public void getTodayDate()
    {
        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
        etDate.setText(fDate);
        model.setDate(fDate);
        model.setMobile("1");
    }

    public void getProjectData()
    {
        dialog.show();
        service.getUserProjectTimesheet(app.getSessionManager().getToken(), model).enqueue(new Callback<UserProjectTimesheetResponse>() {
            @Override
            public void onResponse(Call<UserProjectTimesheetResponse> call, final Response<UserProjectTimesheetResponse> response)
            {
                dialog.dismiss();
                Log.i("Response",response.body().getUserProject().get(0).getProjectName());
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(AddTimesheetActivity.this);
                builderSingle.setTitle("Select project");
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddTimesheetActivity.this, android.R.layout.select_dialog_singlechoice);
                for (int i=0; i<= response.body().getUserProject().size()-1;i++)
                {
                    arrayAdapter.add(response.body().getUserProject().get(i).getProjectName());
                }

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        etProject.setText(arrayAdapter.getItem(position));
                        projectId = response.body().getUserProject().get(position).getProjectId();
                        dialog.dismiss();
                    }
                });
                builderSingle.show();
            }

            @Override
            public void onFailure(Call<UserProjectTimesheetResponse> call, Throwable t)
            {

            }
        });
    }

    public void getTaskList()
    {
        dialog.show();
        TaskListTimesheetSenderModel model = new TaskListTimesheetSenderModel();
        model.setMobile("1");
        model.setProjectId(projectId);
        service.getTaskTimeSheet(app.getSessionManager().getToken(), model).enqueue(new Callback<TaskAddTimeSheetResponse>()
        {
            @Override
            public void onResponse(Call<TaskAddTimeSheetResponse> call, final Response<TaskAddTimeSheetResponse> response)
            {
                dialog.dismiss();
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(AddTimesheetActivity.this);
                builderSingle.setTitle("Select task");
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddTimesheetActivity.this, android.R.layout.select_dialog_singlechoice);
                for (int i=0; i<= response.body().getTask().size()-1;i++)
                {
                    arrayAdapter.add(response.body().getTask().get(i).getTaskName());
                }

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        etTask.setText(arrayAdapter.getItem(position));
                        wpId = response.body().getTask().get(position).getWpId();
                        dialog.dismiss();
                    }
                });
                builderSingle.show();
            }

            @Override
            public void onFailure(Call<TaskAddTimeSheetResponse> call, Throwable t)
            {

            }
        });
    }

    public void addTimeSheet()
    {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Sending...");
        dialog.show();
        AddTimeSheetModel model = new AddTimeSheetModel();
        model.setMobile("1");
        model.setHour(etWorkHour.getText().toString() + "");
        model.setLatitude("0");
        model.setLongtitude("0");
        model.setTsDate(this.model.getDate());
        model.setTsMessage(etMessage.getText().toString());
        model.setTsSubject(etSubject.getText().toString());
        model.setWpId(wpId);

        service.addTimeSheet(app.getSessionManager().getToken(), model).enqueue(new Callback<AddTimeSheetResponse>()
        {
            @Override
            public void onResponse(Call<AddTimeSheetResponse> call, Response<AddTimeSheetResponse> response)
            {
                Log.i("success", response.body().getStatus());
                dialog.dismiss();
                Toast.makeText(AddTimesheetActivity.this, "Succesful add timesheet", Toast.LENGTH_SHORT).show();
                AddTimesheetActivity.this.finish();
            }

            @Override
            public void onFailure(Call<AddTimeSheetResponse> call, Throwable t)
            {
                Log.i("failed", t.toString());
            }
        });
    }

}

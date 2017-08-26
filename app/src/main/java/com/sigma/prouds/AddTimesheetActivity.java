package com.sigma.prouds;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.AddTimeSheetModel;
import com.sigma.prouds.model.ProjectAssignmentModel;
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
    private RelativeLayout rlAdd, rlAddDate;
    private String projectId;
    private String wpId;
    private ProgressDialog dialog;
    private ImageView ivBack;
    private ProjectAssignmentModel projectModel;
    private String returnDate;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_add_timesheet;
    }

    @Override
    protected void workingSpace()
    {
        assignXML();

        projectModel = new ProjectAssignmentModel();

        if (getIntent().getBundleExtra("model") != null)
        {
            Bundle bundle = new Bundle();
            bundle = getIntent().getBundleExtra("model");
            projectModel = (ProjectAssignmentModel) bundle.getSerializable("model");
            etProject.setText(projectModel.getProjectName());
            etTask.setText(projectModel.getWbsName());
            projectId = projectModel.getProjectId();
            wpId = projectModel.getWpId();
        }

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait");
        app = (ProudsApplication) getApplicationContext();
        service = ApiUtils.apiService();
        model = new ProjectListTimesheetSenderModel();
        getTodayDate();
        chooseDate();
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
                if (etProject.getText().length() == 0 || etTask.getText().length() == 0 || etSubject.getText().length() == 0 || etMessage.getText().length() == 0 || etWorkHour.getText().length() == 0)
                {
                    Toast.makeText(AddTimesheetActivity.this, "Form must be filled", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    addTimeSheet();
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTimesheetActivity.this.finish();
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
        rlAddDate = (RelativeLayout) findViewById(R.id.rl_addts_date);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        query.id(R.id.tv_title_toolbar_addts).typeface(Typeface.createFromAsset(getAssets(), "lato_black.ttf"));
        query.id(R.id.tv_addts_new).typeface(Typeface.createFromAsset(getAssets(), "lato_regular.ttf"));
    }

    public void getTodayDate()
    {
        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
        etDate.setText(fDate);
        model.setDate(fDate);
        model.setMobile("1");
    }

////// UPDATE 25 AGT
    public void chooseDate() {
        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String dataFormat = "yyy-MM-dd";
                SimpleDateFormat sdfData = new SimpleDateFormat(dataFormat);
                etDate.setText(sdfData.format(calendar.getTime()));
                model.setDate(sdfData.format(calendar.getTime()));
                model.setMobile("1");
            }
        };

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddTimesheetActivity.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
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
                    arrayAdapter.add(response.body().getTask().get(i).getWbsName());
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
        returnDate = model.getDate();
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Sending...");
        dialog.show();
        final AddTimeSheetModel model = new AddTimeSheetModel();
        model.setMobile("1");
        model.setHour(etWorkHour.getText().toString() + "");
        model.setLatitude("0");
        model.setLongtitude("0");
        model.setTsDate(this.model.getDate());
        model.setTsMessage(etMessage.getText().toString());
        model.setTsSubject(etSubject.getText().toString());
        model.setWpId(wpId);
        model.setProjectId(projectId);

        service.addTimeSheet(app.getSessionManager().getToken(), model).enqueue(new Callback<AddTimeSheetResponse>()
        {
            @Override
            public void onResponse(Call<AddTimeSheetResponse> call, Response<AddTimeSheetResponse> response)
            {
                //Log.i("success", response.body().getStatus());
                dialog.dismiss();
                if (response.code() == 200)
                {
                    Toast.makeText(AddTimesheetActivity.this, "Succesful add timesheet", Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("date", returnDate);
                    setResult(Activity.RESULT_OK,returnIntent);
                    AddTimesheetActivity.this.finish();
                }
                else if(response.code() == 400)
                {
                    Toast.makeText(AddTimesheetActivity.this, "Status project tidak dalam in progress atau on hold", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddTimeSheetResponse> call, Throwable t)
            {
                Log.i("failed", t.toString());
            }
        });
    }

}

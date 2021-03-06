package com.sigma.prouds;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.AddTimeSheetModel;
import com.sigma.prouds.model.ProjectActivityModel;
import com.sigma.prouds.model.ProjectAssignmentModel;
import com.sigma.prouds.model.ProjectListTimesheetSenderModel;
import com.sigma.prouds.model.ResubmitTimeSheetModel;
import com.sigma.prouds.model.TaskListTimesheetSenderModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.AddTimeSheetResponse;
import com.sigma.prouds.network.response.TaskAddTimeSheetResponse;
import com.sigma.prouds.network.response.UserProjectTimesheetResponse;
import com.sigma.prouds.util.GPSTracker;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTimesheetActivity extends BaseActivity {

    public static final String KEY_DATE = "key_date";

    private ProudsApplication app;
    private ApiService service;
    private ProjectListTimesheetSenderModel model;
    private EditText etDate, etProject, etTask, etWorkHour, etSubject, etMessage;
    private RelativeLayout rlAdd, rlAddDate;
    private String projectId;
    private String wpId;
    private String tsId;
    private ProgressDialog dialog;
    private ImageView ivBack;
    private ProjectAssignmentModel projectModel;
    private String returnDate;
    private String curDate;
    private TextView tvActionBarTitle;
    private GPSTracker tracker;

    private ProjectActivityModel resubmitModel;

    private boolean resubmit;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_add_timesheet;
    }

    @Override
    protected void workingSpace()
    {
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        assignXML();
        tracker = new GPSTracker(this);



        projectModel = new ProjectAssignmentModel();

        if (getIntent().getStringExtra(KEY_DATE) != null)
        {
            curDate = getIntent().getStringExtra(KEY_DATE);
        }
        else
        {
            Date cDate = new Date();
            String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
            curDate = fDate;
        }

        if (getIntent().getBundleExtra("model") != null)
        {
            tvActionBarTitle.setText("RESUBMIT TIMESHEET");
            Bundle bundle = new Bundle();
            bundle = getIntent().getBundleExtra("model");
            resubmitModel = (ProjectActivityModel) bundle.getSerializable("model");
            etProject.setText(resubmitModel.getProjectName());
            etTask.setText(resubmitModel.getWbsName());
            projectId = resubmitModel.getProjectId();
            wpId = resubmitModel.getWp();
            tsId = resubmitModel.getTsId();
            resubmit = true;
            Log.i("Resubmit", tsId + "");
            Log.i("Wp id", wpId + "");
            Log.i("project id", projectId + "");
        }

        if (getIntent().getBundleExtra("assignment") != null)
        {
            tvActionBarTitle.setText("ADD TIMESHEET");
            Bundle bundle = new Bundle();
            bundle = getIntent().getBundleExtra("assignment");
            resubmitModel = (ProjectActivityModel) bundle.getSerializable("model");
            etProject.setText(resubmitModel.getProjectName());
            etTask.setText(resubmitModel.getWbsName());
            projectId = resubmitModel.getProjectId();
            wpId = resubmitModel.getWp();
            resubmit = false;
            Log.i("Wp id", wpId + "");
            Log.i("project id", projectId + "");
        }

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait");
        dialog.setCancelable(false);
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
                    if (!resubmit)
                    {
                        addTimeSheet();
                    }
                    else
                    {
                        resubmitTimesheet();
                    }

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
        tvActionBarTitle = (TextView) findViewById(R.id.tv_title_toolbar_addts);
        query.id(R.id.tv_title_toolbar_addts).typeface(Typeface.createFromAsset(getAssets(), "lato_black.ttf"));
        query.id(R.id.tv_addts_new).typeface(Typeface.createFromAsset(getAssets(), "lato_regular.ttf"));
    }

    public void getTodayDate()
    {
        etDate.setText(curDate);
        model.setDate(curDate);
        model.setMobile("1");
    }

////// UPDATE 25 AGT
    public void chooseDate() {
        final Calendar calendar = Calendar.getInstance();
        final Calendar calendarMin = Calendar.getInstance();
        calendarMin.add(Calendar.MONTH, -2);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(AddTimesheetActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMinDate(calendarMin.getTimeInMillis());
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();
                
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
                //Log.i("Response",response.body().getUserProject().get(0).getProjectName());
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
                        etTask.setText("");
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
                    if (response.body().getTask().get(i).getWbsName() != null)
                    {
                        arrayAdapter.add(response.body().getTask().get(i).getWbsName());
                    }

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
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        returnDate = model.getDate();
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Sending...");
        dialog.setCancelable(false);
        dialog.show();
        final AddTimeSheetModel model = new AddTimeSheetModel();
        model.setMobile("1");
        model.setHour(etWorkHour.getText().toString() + "");
        model.setLatitude(tracker.getLatitude() + "");
        model.setLongtitude(tracker.getLongitude() + "");


        try
        {
            addresses = geocoder.getFromLocation(tracker.getLatitude(), tracker.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();

            String finalAddress = address + " " + city + " " + state + " " + country + " " + postalCode;
            model.setAddress(finalAddress);
            Log.i("Adress", finalAddress);
        }
        catch (Exception e)
        {
            model.setAddress("");
        }

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
                dialog.dismiss();
                Log.i("failed", t.toString());
            }
        });
    }

    public void resubmitTimesheet()
    {
        returnDate = model.getDate();
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Resubmit...");
        dialog.show();
        final ResubmitTimeSheetModel model = new ResubmitTimeSheetModel();
        model.setMobile("1");
        model.setHour(etWorkHour.getText().toString() + "");
        model.setLatitude("0");
        model.setLongtitude("0");
        model.setTsDate(this.model.getDate());
        model.setTsMessage(etMessage.getText().toString());
        model.setTsSubject(etSubject.getText().toString());
        model.setWpId(wpId);
        model.setProjectId(projectId);
        model.setTsId(tsId);

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try
        {
            addresses = geocoder.getFromLocation(tracker.getLatitude(), tracker.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();

            String finalAddress = address + " " + city + " " + state + " " + country + " " + postalCode;
            model.setAddress(finalAddress);
        }
        catch (Exception e)
        {
            model.setAddress("");
        }

        service.resubmit(app.getSessionManager().getToken(), model).enqueue(new Callback<AddTimeSheetResponse>() {
            @Override
            public void onResponse(Call<AddTimeSheetResponse> call, Response<AddTimeSheetResponse> response) {
                dialog.dismiss();
                if (response.code() == 200)
                {
                    Toast.makeText(AddTimesheetActivity.this, "Succesful resubmit timesheet", Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<AddTimeSheetResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(AddTimesheetActivity.this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

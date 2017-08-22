package com.sigma.prouds;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.UploadProjectIssueResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormReportIssueActivity extends BaseActivity {

    public static int REQUEST_SELECT_IMAGE_IN_ALBUM = 0;
    private EditText etIssueUpload;
    private EditText etPriority;
    private EditText etSubject;
    private EditText etMessage;
    private Uri imagePath;
    private String projectId;
    private ProgressDialog dialog;
    private ImageView ivBack;

    private ProudsApplication app;
    private ApiService service;

    private RelativeLayout rlUploadIssue;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_form_report_issue;
    }

    @Override
    protected void workingSpace()
    {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Uploading...");
        service = ApiUtils.apiService();
        app = (ProudsApplication) getApplicationContext();
        projectId = getIntent().getExtras().getString("project_id");
        assignXML();
        etIssueUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });

        etPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPriority();
            }
        });

        rlUploadIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadIssue();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormReportIssueActivity.this.finish();
            }
        });

    }

    public void assignXML()
    {
        etIssueUpload = (EditText) findViewById(R.id.et_issue_upload);
        etPriority = (EditText) findViewById(R.id.et_issue_priority);
        rlUploadIssue = (RelativeLayout) findViewById(R.id.rl_report_issue);
        etSubject = (EditText) findViewById(R.id.et_issue_subject);
        etMessage = (EditText) findViewById(R.id.et_issue_msg);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        query.id(R.id.tv_title_toolbar_report_issue).typeface(Typeface.createFromAsset(getAssets(), "lato_black.ttf"));
        query.id(R.id.tv_report_issue).typeface(Typeface.createFromAsset(getAssets(), "lato_regular.ttf"));
    }

    public void getImage()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM);
        }
    }

    public void getPriority()
    {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle("Select priority");
        final String[] arrayCategory = {"High", "Medium", "Low"};
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, arrayCategory);

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                switch (position)
                {
                    case 0:
                        etPriority.setText("High");
                        break;

                    case 1:
                        etPriority.setText("Medium");
                        break;

                    case 2:
                        etPriority.setText("Low");
                        break;


                }
                dialog.dismiss();
            }
        });
        builderSingle.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM)
        {
            if (data != null) {
                Uri contentURI = data.getData();
                imagePath = contentURI;
                try {
                    //Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    etIssueUpload.setText(contentURI.getLastPathSegment());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void uploadIssue()
    {
        dialog.show();
        File file = new File(imagePath.getPath());
        final RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file_upload", file.getName(), requestFile);

        RequestBody id =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), projectId);

        RequestBody subject =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), etSubject.getText().toString());

        RequestBody message =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), etMessage.getText().toString());

        RequestBody priority =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), etPriority.getText().toString());

        service.uploadIssue(app.getSessionManager().getToken(), id, subject, message, priority, body).enqueue(new Callback<UploadProjectIssueResponse>() {
            @Override
            public void onResponse(Call<UploadProjectIssueResponse> call, Response<UploadProjectIssueResponse> response)
            {
                dialog.dismiss();
                Toast.makeText(FormReportIssueActivity.this, "Upload succesful", Toast.LENGTH_SHORT).show();
                FormReportIssueActivity.this.finish();
                //Log.i("Upload status ", response.body().getStatus());
            }

            @Override
            public void onFailure(Call<UploadProjectIssueResponse> call, Throwable t)
            {
                dialog.dismiss();
                Toast.makeText(FormReportIssueActivity.this, "Upload failed, check internet connection", Toast.LENGTH_SHORT).show();
                Log.i("Failed status ", t.toString());
            }
        });

    }
}

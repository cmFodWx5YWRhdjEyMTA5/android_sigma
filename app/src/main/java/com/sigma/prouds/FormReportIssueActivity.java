package com.sigma.prouds;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
            public void onClick(View v)
            {
                if (etSubject.getText().length() == 0 || etMessage.getText().length() == 0 || etPriority.getText().length() == 0 || etIssueUpload.getText().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Form must be filled", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    uploadIssue();
                }

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
                Log.i("Path : ", getImagePathFromInputStreamUri(imagePath));
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
        File file = new File(getImagePathFromInputStreamUri(imagePath));
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
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
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

    public String getImagePathFromInputStreamUri(Uri uri) {
        InputStream inputStream = null;
        String filePath = null;

        if (uri.getAuthority() != null) {
            try {
                inputStream = getContentResolver().openInputStream(uri); // context needed
                File photoFile = createTemporalFileFrom(inputStream);

                filePath = photoFile.getPath();

            } catch (FileNotFoundException e) {
                // log
            } catch (IOException e) {
                // log
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return filePath;
    }

    private File createTemporalFileFrom(InputStream inputStream) throws IOException {
        File targetFile = null;

        if (inputStream != null) {
            int read;
            byte[] buffer = new byte[8 * 1024];

            targetFile = createTemporalFile();
            OutputStream outputStream = new FileOutputStream(targetFile);

            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();

            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return targetFile;
    }

    private File createTemporalFile() {
        return new File(getExternalCacheDir(), "tempFile.jpg"); // context needed
    }
}

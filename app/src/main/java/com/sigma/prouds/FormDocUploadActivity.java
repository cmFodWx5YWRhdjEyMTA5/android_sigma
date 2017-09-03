package com.sigma.prouds;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.UploadProjectDocResponse;

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

public class FormDocUploadActivity extends BaseActivity {

    private static int REQUEST_SELECT_FILE = 0;

    private EditText etDesc, etDocUpload;
    private RelativeLayout rlUploadDoc;

    private Uri filePath;
    private String projectId;
    private ProgressDialog dialog;
    private ProudsApplication app;
    private ApiService service;
    private ImageView ivBack;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_form_doc_upload;
    }

    @Override
    protected void workingSpace()
    {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Uploading...");
        dialog.setCancelable(false);
        service = ApiUtils.apiService();
        app = (ProudsApplication) getApplicationContext();
        projectId = getIntent().getExtras().getString("project_id");
        assignXML();

        etDocUpload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getFile();
            }
        });

        rlUploadDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDocUpload.getText().length() == 0 || etDesc.getText().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Fill must be fill", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    uploadDoc();
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormDocUploadActivity.this.finish();
            }
        });
    }

    public void assignXML()
    {
        rlUploadDoc = (RelativeLayout) findViewById(R.id.rl_upload_doc);
        etDesc = (EditText) findViewById(R.id.et_doc_desc);
        etDocUpload = (EditText) findViewById(R.id.et_doc_upload);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        query.id(R.id.tv_title_toolbar_add_doc).typeface(Typeface.createFromAsset(getAssets(), "lato_black.ttf"));
        query.id(R.id.tv_doc_upload).typeface(Typeface.createFromAsset(getAssets(), "lato_regular.ttf"));
    }

    public void getFile()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, REQUEST_SELECT_FILE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_FILE)
        {
            if (data != null)
            {
                Uri contentURI = data.getData();
                filePath = contentURI;
                try {
                    etDocUpload.setText(contentURI.getLastPathSegment());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void uploadDoc()
    {
        dialog.show();
        RequestBody desc =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), etDesc.getText().toString());

        File file = new File(filePath.getPath());
        final RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("document", file.getName(), requestFile);

        service.uploadDoc(app.getSessionManager().getToken(), projectId, desc, body).enqueue(new Callback<UploadProjectDocResponse>()
        {
            @Override
            public void onResponse(Call<UploadProjectDocResponse> call, Response<UploadProjectDocResponse> response)
            {
                dialog.dismiss();
                Log.i("title ", response.body().getTitle() + "");
                Log.i("message ", response.body().getMessage() + "");
                if (response.body().getTitle().equalsIgnoreCase("error"))
                {
                    Toast.makeText(FormDocUploadActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(FormDocUploadActivity.this, "Upload succesful", Toast.LENGTH_SHORT).show();
                    FormDocUploadActivity.this.finish();
                }

            }

            @Override
            public void onFailure(Call<UploadProjectDocResponse> call, Throwable t)
            {
                dialog.dismiss();
                Toast.makeText(FormDocUploadActivity.this, "Upload failed, check internet connection", Toast.LENGTH_SHORT).show();
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

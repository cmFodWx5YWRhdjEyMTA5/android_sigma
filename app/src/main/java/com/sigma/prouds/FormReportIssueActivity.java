package com.sigma.prouds;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.sigma.prouds.base.BaseActivity;

public class FormReportIssueActivity extends BaseActivity {

    public static int REQUEST_SELECT_IMAGE_IN_ALBUM = 0;
    private EditText etIssueUpload;
    private EditText etPriority;
    private String imagePath;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_form_report_issue;
    }

    @Override
    protected void workingSpace()
    {
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

    }

    public void assignXML()
    {
        etIssueUpload = (EditText) findViewById(R.id.et_issue_upload);
        etPriority = (EditText) findViewById(R.id.et_issue_priority);
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
                try {
                    //Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    etIssueUpload.setText(contentURI.getLastPathSegment());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

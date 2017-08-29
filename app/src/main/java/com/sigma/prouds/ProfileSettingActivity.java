package com.sigma.prouds;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.EditProfileSendModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.EditProfileResponse;
import com.sigma.prouds.network.response.MyActivityResponse;
import com.sigma.prouds.network.response.ProjectResponse;
import com.sigma.prouds.network.response.UserdataResponse;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSettingActivity extends BaseActivity {

    private TextView tvUsername, tvSave;
    private LinearLayout llLogout;
    private ProudsApplication app;
    private Typeface latoRegular;
    private RelativeLayout rlProfileSetting;
    private ImageView ivBack, ivNotif;
    private ProgressDialog dialog;

    private EditText etUserId, etRole, etFullName, etEmail, etPhone, etAddress;
    private CircleImageView ivDp;

    private ApiService service;

    public static final int ACTION_REQUEST_CAMERA = 1;
    public static final int ACTION_REQUEST_GALERY = 2;

    private String mCurrentPhotoPath;
    private String imagePath;

    @Override
    protected int getLayout() {
        return R.layout.activity_profile_setting;
    }

    @Override
    protected void workingSpace()
    {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        assignXML();
        app = (ProudsApplication) getApplicationContext();
        service = ApiUtils.apiService();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        Intent intent = getIntent();
        tvUsername.setText(intent.getExtras().getString("user"));
        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        latoRegular = Typeface.createFromAsset(this.getAssets(), "lato_regular.ttf");
        setFontForContainer(rlProfileSetting);
        query.id(R.id.tv_username).typeface(Typeface.createFromAsset(this.getAssets(), "lato_black.ttf"));

        ivBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ProfileSettingActivity.this.finish();
            }
        });

        ivNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNotif();
            }
        });

        getProfileData();

        tvSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveProfileChange();
            }
        });

    }

    public void assignXML()
    {
        tvUsername = (TextView) findViewById(R.id.tv_username);
        llLogout = (LinearLayout) findViewById(R.id.ll_profset_logout);
        rlProfileSetting = (RelativeLayout) findViewById(R.id.rl_profile_setting);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivNotif = (ImageView) findViewById(R.id.iv_notif);

        etUserId = (EditText) findViewById(R.id.et_profset_uid);
        etRole = (EditText) findViewById(R.id.et_profset_role);
        etFullName = (EditText) findViewById(R.id.et_profset_name);
        etEmail = (EditText) findViewById(R.id.et_profset_email);
        etPhone = (EditText) findViewById(R.id.et_profset_phone);
        etAddress = (EditText) findViewById(R.id.et_profset_address);

        tvSave = (TextView) findViewById(R.id.tv_profset_save);
        ivDp = (CircleImageView) findViewById(R.id.iv_dp);

        ivDp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileSettingActivity.this);
                builder.setTitle("Choose source");
                builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        takePicture();
                    }
                });
                builder.setNegativeButton("Galery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pickFromGallery();
                    }
                });
                builder.show();
            }
        });

    }

    public void takePicture()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                /*Uri photoURI = FileProvider.getUriForFile(this,
                        "com.dattabot.surveygisclick",
                        photoFile);*/
                Uri photoURI = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, ACTION_REQUEST_CAMERA);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        imagePath = image.getAbsolutePath();
        return image;
    }

    public void pickFromGallery()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, ACTION_REQUEST_GALERY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case ACTION_REQUEST_CAMERA:
                if (resultCode == Activity.RESULT_OK)
                {
                    imagePath = mCurrentPhotoPath;
                    File mediaFile = new File(imagePath);
                    Glide.with(this).load(mediaFile).asBitmap().into(ivDp);

                }
                break;

            case ACTION_REQUEST_GALERY:
                if (resultCode == Activity.RESULT_OK)
                {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            Glide.with(this).load(bitmap).asBitmap().into(ivDp);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
        }
    }

    public void saveProfileChange()
    {
        dialog.show();
        EditProfileSendModel model = new EditProfileSendModel();
        model.setNoHp(etPhone.getText().toString());
        model.setAddress(etAddress.getText().toString());

        service.editProfile(app.getSessionManager().getToken(), model).enqueue(new Callback<EditProfileResponse>()
        {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response)
            {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                ProfileSettingActivity.this.finish();
            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t)
            {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Update failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void toNotif() {
        Intent intent = new Intent(this, NotifActivity.class);
        startActivity(intent);
    }

    public void logout()
    {
        app.getSessionManager().clearSession();
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        ProfileSettingActivity.this.finish();
    }

    private void setFontForContainer(ViewGroup contentLayout) {
        for (int i=0; i < contentLayout.getChildCount(); i++) {
            View view = contentLayout.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView)view).setTypeface(latoRegular);
            }
            else if (view instanceof ViewGroup) {
                setFontForContainer((ViewGroup) view);
            }
        }
    }

    public void getProfileData()
    {
        dialog.show();
        service.getUserdata(app.getSessionManager().getToken()).enqueue(new Callback<UserdataResponse>()
        {
            @Override
            public void onResponse(Call<UserdataResponse> call, Response<UserdataResponse> response)
            {
                dialog.dismiss();
                etUserId.setText(response.body().getUserdata().getUserId());
                etEmail.setText(response.body().getUserdata().getEmail());
                etFullName.setText(response.body().getUserdata().getUserName());
                etRole.setText(response.body().getUserdata().getProfId());
                etPhone.setText(response.body().getUserdata().getPhoneNo());
                etAddress.setText(response.body().getUserdata().getAddress());
            }

            @Override
            public void onFailure(Call<UserdataResponse> call, Throwable t)
            {
                dialog.dismiss();
            }
        });
    }

}

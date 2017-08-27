package com.sigma.prouds;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.MyActivityResponse;
import com.sigma.prouds.network.response.ProjectResponse;
import com.sigma.prouds.network.response.UserdataResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSettingActivity extends BaseActivity {

    private TextView tvUsername;
    private LinearLayout llLogout;
    private ProudsApplication app;
    private Typeface latoRegular;
    private RelativeLayout rlProfileSetting;
    private ImageView ivBack, ivNotif;
    private ProgressDialog dialog;

    private EditText etUserId, etRole, etFullName, etEmail, etPhone, etAddress;

    private ApiService service;

    @Override
    protected int getLayout() {
        return R.layout.activity_profile_setting;
    }

    @Override
    protected void workingSpace()
    {
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

package com.sigma.prouds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sigma.prouds.base.BaseActivity;

public class ProfileSettingActivity extends BaseActivity {

    private TextView tvUsername;
    private LinearLayout llLogout;
    private ProudsApplication app;

    @Override
    protected int getLayout() {
        return R.layout.activity_profile_setting;
    }

    @Override
    protected void workingSpace()
    {
        assignXML();
        app = (ProudsApplication) getApplicationContext();
        Intent intent = getIntent();
        tvUsername.setText(intent.getExtras().getString("user"));
        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    public void assignXML()
    {
        tvUsername = (TextView) findViewById(R.id.tv_username);
        llLogout = (LinearLayout) findViewById(R.id.ll_profset_logout);
    }

    public void logout()
    {
        app.getSessionManager().clearSession();
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        ProfileSettingActivity.this.finish();
    }

}

package com.sigma.prouds;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sigma.prouds.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    public ProudsApplication app;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void workingSpace() {
        this.app = (ProudsApplication) getApplication();

        new Handler().postDelayed(new Runnable() { // login checking process starts here
            @Override
            public void run()
            {
                if (app.getSessionManager().isLogin()) // user has login already
                {
                    Intent intent = new Intent(SplashActivity.this, PagerActivity.class); // intent use to move to another activity, this activity goes to MainActivity
                    startActivity(intent);
                    SplashActivity.this.finish(); // close SplashActivity
                }
                else // user hasn't login yet
                {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class); // intent use to move to another activity, this activity goes to SignInActivity
                    startActivity(intent);
                    SplashActivity.this.finish(); // close SplashActivity
                }
            }
        }, 2000);
    }
}

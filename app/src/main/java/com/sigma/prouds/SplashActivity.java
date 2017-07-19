package com.sigma.prouds;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.LoginModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    public ProudsApplication app;
    private ApiService service;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void workingSpace() {
        this.app = (ProudsApplication) getApplication();
        service = ApiUtils.apiService();
        new Handler().postDelayed(new Runnable() { // login checking process starts here
            @Override
            public void run()
            {
                if (app.getSessionManager().isLogin()) // user has login already
                {
                    LoginModel model = new LoginModel();
                    model.setUserId(app.getSessionManager().getUserName());
                    model.setPassword(app.getSessionManager().getPassword());
                    model.setFpid("160927084946");

                    service.login(model).enqueue(new Callback<LoginResponse>()
                    {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response)
                        {
                            app.getSessionManager().setToken(response.body().getToken());
                            Intent intent = new Intent(SplashActivity.this, PagerActivity.class);
                            startActivity(intent);
                            SplashActivity.this.finish();
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t)
                        {

                        }
                    });

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

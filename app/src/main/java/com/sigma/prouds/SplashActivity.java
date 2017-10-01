package com.sigma.prouds;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.LoginModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.LoginResponse;

import io.fabric.sdk.android.Fabric;
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
        //Fabric.with(this, new Crashlytics());
        //forceCrash();
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
                            if (response.code() == 200)
                            {
                                Log.i("Privilege", response.body().getPrivilege().isTimesheetApproval() + "");
                                Log.i("Unread ", response.body().getNotifInfo().getTotalUnread() + "");
                                app.getSessionManager().setToken(response.body().getToken());
                                app.getSessionManager().setRoleName(response.body().getUserdata().getProfileName());
                                app.getSessionManager().setPrivilageTimesheet(response.body().getPrivilege().isTimesheetApproval());
                                app.getSessionManager().setPrivilageEditProject(response.body().getPrivilege().isEditProject());
                                app.getSessionManager().setPrivilageUploadDoc(response.body().getPrivilege().isUploadDoc());
                                app.getSessionManager().setPrivilageUploadIssue(response.body().getPrivilege().isUploadIssue());
                                app.getSessionManager().setUnreadNotification(Integer.parseInt(response.body().getNotifInfo().getTotalUnread()));

                                //app.getSessionManager().setUnreadNotification(1);

                                Intent intent = new Intent(SplashActivity.this, PagerActivity.class);
                                startActivity(intent);
                                SplashActivity.this.finish();
                            }

                            else if (response.code() == 500)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                                builder.setTitle("Error");
                                builder.setMessage("Internal server error, try again later");

                                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        SplashActivity.this.finish();
                                    }
                                });
                                builder.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                            builder.setTitle("Error");
                            builder.setMessage("No internet connection");

                            builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SplashActivity.this.finish();
                                }
                            });
                            builder.show();
                        }
                    });
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

    public void forceCrash() {
        throw new RuntimeException("This is a crash");
    }
}

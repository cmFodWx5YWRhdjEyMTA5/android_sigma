package com.sigma.prouds;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.LoginModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.LoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity
{
    private ApiService service;
    private ProgressDialog dialog;
    private ProudsApplication app;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_login;
    }

    @Override
    protected void workingSpace()
    {
        this.app = (ProudsApplication) getApplication();

        if (app.getSessionManager().isLogin())
        {
            Intent intent = new Intent(LoginActivity.this, PagerActivity.class);
            startActivity(intent);
        }

        service = ApiUtils.apiService();
        initDialog();
        query.id(R.id.bt_signin).clicked(this, "sendLogin");
    }

    public void sendLogin()
    {
        dialog.show();
        final LoginModel model = new LoginModel();
        model.setUserId(query.id(R.id.et_username).getText().toString());
        model.setPassword(query.id(R.id.et_password).getText().toString());
        model.setFpid("160927084946");
        service.login(model).enqueue(new Callback<LoginResponse>()
        {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response)
            {
                dialog.dismiss();
                app.getSessionManager().setLogin(true);
                app.getSessionManager().setToken(response.body().getToken());
                app.getSessionManager().setUserName(model.getUserId());
                app.getSessionManager().setPassword(model.getPassword());
                Log.i("Token", app.getSessionManager().getToken());
                Intent intent = new Intent(LoginActivity.this, PagerActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t)
            {
                Log.i("TAG", t.toString());
                Toast.makeText(LoginActivity.this, "Error login", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    public void initDialog()
    {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Signing in...");
    }

}

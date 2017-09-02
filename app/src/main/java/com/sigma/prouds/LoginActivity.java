package com.sigma.prouds;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText etUserName;
    private EditText etPassword;
    private Button btLogin;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_login;
    }

    @Override
    protected void workingSpace()
    {
        this.app = (ProudsApplication) getApplication();
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btLogin = (Button) findViewById(R.id.bt_signin);
        if (app.getSessionManager().isLogin())
        {
            Intent intent = new Intent(LoginActivity.this, PagerActivity.class);
            startActivity(intent);
        }

        service = ApiUtils.apiService();
        initDialog();

            btLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etUserName.getText().length() == 0 || etPassword.getText().length() == 0)
                    {
                        Toast.makeText(LoginActivity.this, "Username and password must be filled", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        sendLogin();
                    }

                }
            });

        //query.id(R.id.bt_signin).clicked(this, "sendLogin");
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
                if (response.code() == 200)
                {
                    dialog.dismiss();
                    app.getSessionManager().setLogin(true);
                    app.getSessionManager().setToken(response.body().getToken());
                    app.getSessionManager().setUserName(model.getUserId());
                    app.getSessionManager().setPassword(model.getPassword());
                    app.getSessionManager().setProfId(response.body().getUserdata().getProfId());
                    app.getSessionManager().setRoleName(response.body().getUserdata().getProfileName());
                    app.getSessionManager().setPrivilageTimesheet(response.body().getPrivilege().isAccDenyTimesheet());
                    Log.i("Token", app.getSessionManager().getToken());
                    Intent intent = new Intent(LoginActivity.this, PagerActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
                else if (response.code() == 400)
                {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Username Atau Password salah", Toast.LENGTH_SHORT).show();
                }

                else if (response.code() == 500)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Internal server error, try again later");

                    builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LoginActivity.this.finish();
                        }
                    });
                    builder.show();
                }

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

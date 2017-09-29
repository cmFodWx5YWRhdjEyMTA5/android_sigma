package com.sigma.prouds;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sigma.prouds.adapter.NotifAdapter;
import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.NotificationResponse;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/7/2017.
 */

public class NotifActivity extends BaseActivity {

    private RecyclerView rvNotif;
    private NotifAdapter adapter;
    private TextView tvEmptyNotif;
    private ImageView ivBack;
    private ProudsApplication app;
    private ApiService service;
    private ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_notif;
    }

    @Override
    protected void workingSpace() {

        app = (ProudsApplication) getApplicationContext();
        service = ApiUtils.apiService();

        rvNotif = (RecyclerView) findViewById(R.id.rv_notif);
        tvEmptyNotif = (TextView) findViewById(R.id.tv_notif_empty);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvNotif.setLayoutManager(layoutManager);
        rvNotif.setAdapter(adapter);

        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                NotifActivity.this.finish();
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");

        getData();
    }

    public void getData()
    {
        dialog.show();
        service.getNotification(app.getSessionManager().getToken()).enqueue(new Callback<NotificationResponse>()
        {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response)
            {
                dialog.dismiss();
                adapter = new NotifAdapter(NotifActivity.this, response.body().getNotifList());
                rvNotif.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t)
            {
                dialog.dismiss();
            }
        });
    }
}

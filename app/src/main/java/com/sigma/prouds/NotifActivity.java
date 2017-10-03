package com.sigma.prouds;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    public static final String KEY_NOTIF_TO_DETAIL = "key_notif_to_detail";
    public static final String KEY_NOTIF_PROJECT_NAME = "key_notif_project_name";
    public static final String KEY_NOTIF_PROJECT_STATUS = "key_notif_project_status";
    public static final String KEY_NOTIF_PROJECT_COMPLETE = "key_notif_project_complete";

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

    public void onEvent(Bundle bundle)
    {
        if (bundle.containsKey("key_project_id"))
        {
            Log.i("Project name : ", bundle.getString(NotifActivity.KEY_NOTIF_PROJECT_NAME));
            Intent intent = new Intent(NotifActivity.this, ProjectDetailsActivity.class);
            intent.putExtra(PagerActivity.KEY_TO_DETAIL_PROJECT, bundle);
            //intent.putExtra(PagerActivity.KEY_PROJECT_NAME, bundle.getString(NotifActivity.KEY_NOTIF_PROJECT_NAME));
            //intent.putExtra(PagerActivity.KEY_PROJECT_COMPLETED, bundle.getString(NotifActivity.KEY_NOTIF_PROJECT_COMPLETE));
            //intent.putExtra(PagerActivity.KEY_PROJECT_STATUS, bundle.getString(NotifActivity.KEY_NOTIF_PROJECT_STATUS));
            //intent.putExtra(ProjectDetailsActivity.KEY_FROM_NOTIFICATION, true);
            startActivity(intent);
        }
    }
}

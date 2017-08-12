package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.ProjectActivityAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.ProjectActivityResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/15/2017.
 */

public class ActivityFragment extends BaseFragment
{

    private ApiService service;
    static Context ctx;
    private String projectId;
    private ProudsApplication app;
    private RecyclerView rvActivity;
    private ProjectActivityAdapter adapter;

    public static ActivityFragment newInstance(Context context, String projectId)
    {
        ActivityFragment fragment = new ActivityFragment();
        ctx = context;
        Bundle args = new Bundle();
        args.putString("project_id", projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void workingSpace(View view)
    {
        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();
        projectId = getArguments().getString("project_id");
        rvActivity = (RecyclerView) view.findViewById(R.id.rv_activity);
        getActivityData();
    }

    public void getActivityData()
    {
        service.getProjectActivity(projectId, app.getSessionManager().getToken()).enqueue(new Callback<ProjectActivityResponse>()
        {
            @Override
            public void onResponse(Call<ProjectActivityResponse> call, Response<ProjectActivityResponse> response)
            {
                adapter = new ProjectActivityAdapter(ctx, response.body().getProjectActivities());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
                rvActivity.setLayoutManager(mLayoutManager);
                rvActivity.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ProjectActivityResponse> call, Throwable t)
            {

            }
        });
    }
}

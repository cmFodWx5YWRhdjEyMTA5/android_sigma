package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.ProjectWorkPlanAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.model.ProjectWorkplanStatusModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.DetailProjectResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/15/2017.
 */

public class WorkplanFragment extends BaseFragment {

    static Context ctx;
    private String projectId;
    private RecyclerView rvWorkplan;
    private ProjectWorkPlanAdapter adapter;
    private ProudsApplication app;
    private ApiService service;

    public static WorkplanFragment newInstance(Context context, String projectId)
    {
        WorkplanFragment fragment = new WorkplanFragment();
        ctx = context;
        Bundle args = new Bundle();
        args.putString("project_id", projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_workplan;
    }

    @Override
    protected void workingSpace(View view)
    {
        projectId = getArguments().getString("project_id");
        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();

        rvWorkplan = (RecyclerView) view.findViewById(R.id.rv_workplan);
        getWorkPlanData();
    }

    public void getWorkPlanData()
    {
        service.getDetailProject(projectId, app.getSessionManager().getToken()).enqueue(new Callback<DetailProjectResponse>()
        {
            @Override
            public void onResponse(Call<DetailProjectResponse> call, Response<DetailProjectResponse> response)
            {
                adapter = new ProjectWorkPlanAdapter(context, response.body().getProjectWorkplanStatus().getTask());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
                rvWorkplan.setLayoutManager(mLayoutManager);
                rvWorkplan.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<DetailProjectResponse> call, Throwable t)
            {

            }
        });
    }
}

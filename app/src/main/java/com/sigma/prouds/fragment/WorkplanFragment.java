package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.ProjectWorkPlanAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.model.ProjectWorkplanStatusModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.DetailProjectResponse;
import com.sigma.prouds.network.response.WorkplanResponse;

import org.w3c.dom.Text;

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
    private ProgressBar pbWorkplan;
    private TextView tvEmptyWorkplan;

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

        pbWorkplan = (ProgressBar) view.findViewById(R.id.pb_workplan);
        rvWorkplan = (RecyclerView) view.findViewById(R.id.rv_workplan);
        tvEmptyWorkplan = (TextView) view.findViewById(R.id.tv_empty_workplan);
        getWorkPlanData();
    }

    public void getWorkPlanData()
    {
        /*service.getDetailProject(projectId, app.getSessionManager().getToken()).enqueue(new Callback<DetailProjectResponse>()
        {
            @Override
            public void onResponse(Call<DetailProjectResponse> call, Response<DetailProjectResponse> response)
            {
                query.id(pbWorkplan).gone();
                adapter = new ProjectWorkPlanAdapter(context, response.body().getProjectWorkplanStatus().getTask());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
                rvWorkplan.setLayoutManager(mLayoutManager);
                rvWorkplan.setAdapter(adapter);

                if (response.body().getProjectWorkplanStatus().getTask().size() == 0) {
                    tvEmptyWorkplan.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<DetailProjectResponse> call, Throwable t)
            {

            }
        });*/

        service.getWorkplan(app.getSessionManager().getToken(), projectId).enqueue(new Callback<WorkplanResponse>()
        {
            @Override
            public void onResponse(Call<WorkplanResponse> call, Response<WorkplanResponse> response)
            {
                pbWorkplan.setVisibility(View.GONE);
                adapter = new ProjectWorkPlanAdapter(context, response.body().getWorkplan());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
                rvWorkplan.setLayoutManager(mLayoutManager);
                rvWorkplan.setAdapter(adapter);

                if (response.body().getWorkplan().size() == 0) {
                    tvEmptyWorkplan.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<WorkplanResponse> call, Throwable t)
            {

            }
        });
    }
}

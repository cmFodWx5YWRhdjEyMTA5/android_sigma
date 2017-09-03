package com.sigma.prouds.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sigma.prouds.AddTimesheetActivity;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.MyActivityAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.model.ProjectActivityModel;
import com.sigma.prouds.model.ProjectAssignmentModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.MyActivityResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 1414 on 8/7/2017.
 */

public class MyActivityFragment extends BaseFragment
{
    public static final String KEY_TO_TIMESHEET = "key_to_timesheet";
    public static final int KEY_REFRESH = 0;

    static Context ctx;
    private RecyclerView vpActivity;
    private MyActivityAdapter adapter;
    //private List<ProjectActivityModel> list;
    private ApiService service;
    private ProudsApplication app;


    public static MyActivityFragment newInstance(Context context)
    {
        MyActivityFragment fragment = new MyActivityFragment();
        ctx = context;
        return fragment;
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_my_activity;
    }

    @Override
    protected void workingSpace(View view)
    {
        vpActivity = (RecyclerView) view.findViewById(R.id.rv_my_activity);
        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();
        getData();

    }

    public void getData()
    {
        service.getMyActivity(app.getSessionManager().getToken()).enqueue(new Callback<MyActivityResponse>()
        {
            @Override
            public void onResponse(Call<MyActivityResponse> call, Response<MyActivityResponse> response)
            {
                adapter = new MyActivityAdapter(ctx, response.body().getActivityTimesheet());
                vpActivity.setLayoutManager(new LinearLayoutManager(ctx));
                vpActivity.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MyActivityResponse> call, Throwable t)
            {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KEY_REFRESH)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                getData();
            }
        }
    }

    public void onEvent(ProjectActivityModel model)
    {
        ProjectAssignmentModel projectActivityModel = new ProjectAssignmentModel();
        projectActivityModel.setProjectName(model.getProjectName());
        projectActivityModel.setProjectId(model.getProjectId());
        projectActivityModel.setWbsName(model.getWbsName());
        projectActivityModel.setWbsId(model.getWbsId());


        Intent intent = new Intent(getActivity(), AddTimesheetActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", (Serializable) model);
        intent.putExtra("model", bundle);
        startActivityForResult(intent, KEY_REFRESH);
    }
}

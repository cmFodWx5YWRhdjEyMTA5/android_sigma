package com.sigma.prouds.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.AssignmentAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.model.ProjectAssignmentModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.MyAssignmentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/7/2017.
 */

public class AssignmentFragment extends BaseFragment
{
    private ProudsApplication app;
    static Context ctx;
    private ApiService service;
    private RecyclerView rvAssigment;
    private AssignmentAdapter adapter;

    public AssignmentFragment()
    {

    }

    public static AssignmentFragment newInstance(Context context) {
        AssignmentFragment fragment = new AssignmentFragment();
        ctx = context;
        return fragment;
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_assignment;
    }

    @Override
    protected void workingSpace(View view)
    {
        app = (ProudsApplication) ctx.getApplicationContext();
        rvAssigment = (RecyclerView) view.findViewById(R.id.rv_assignment);
        getAssignmentData();
    }

    public void getAssignmentData()
    {
        service = ApiUtils.apiService();
        service.getMyAssignmentResponse(app.getSessionManager().getToken()).enqueue(new Callback<MyAssignmentResponse>()
        {
            @Override
            public void onResponse(Call<MyAssignmentResponse> call, Response<MyAssignmentResponse> response)
            {
                if (query != null)
                {
                    query.id(R.id.pb_assignment).gone();
                }

                setAdapterView(response.body().getAssignment());
                Log.i("Response", response.body().getAssignment().get(0).getProjectName().toString());
            }

            @Override
            public void onFailure(Call<MyAssignmentResponse> call, Throwable t)
            {
                if (query != null)
                {
                    query.id(R.id.pb_assignment).gone();
                }
                Log.i("Response", "Failed : " + t.toString());
            }
        });
    }

    public void setAdapterView(List<ProjectAssignmentModel> list)
    {
        adapter = new AssignmentAdapter(ctx, list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
        rvAssigment.setLayoutManager(mLayoutManager);
        rvAssigment.setAdapter(adapter);
    }
}

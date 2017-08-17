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
import com.sigma.prouds.model.ProjectAssignmentNewModel;
import com.sigma.prouds.model.ProjectDetailModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.MyAssignmentNewResponse;
import com.sigma.prouds.network.response.MyAssignmentResponse;

import java.util.ArrayList;
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
    private List<ProjectDetailModel> listItem;

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
        service.getMyAssignmentResponse(app.getSessionManager().getToken()).enqueue(new Callback<MyAssignmentNewResponse>()
        {
            @Override
            public void onResponse(Call<MyAssignmentNewResponse> call, Response<MyAssignmentNewResponse> response)
            {
                if (query != null)
                {
                    query.id(R.id.pb_assignment).gone();
                }

                listItem = new ArrayList<ProjectDetailModel>();
                for (int i = 0; i <= response.body().getAssignment().size() - 1;i++)
                {
                    for (int j = 0; j <= response.body().getAssignment().get(i).getProjectDetail().size() -1; j++)
                    {
                        //setAdapterView(response.body().getAssignment().get(i).getProjectDetail());
                        listItem.add(response.body().getAssignment().get(i).getProjectDetail().get(j));
                    }
                }
                setAdapterView(listItem);
                //Log.i("Response", response.body().getAssignment().get(0).getProjectName().toString());
            }

            @Override
            public void onFailure(Call<MyAssignmentNewResponse> call, Throwable t)
            {
                if (query != null)
                {
                    query.id(R.id.pb_assignment).gone();
                }
                Log.i("Response", "Failed : " + t.toString());
            }
        });
    }

    public void setAdapterView(List<ProjectDetailModel> list)
    {
        if (adapter == null)
        {
            adapter = new AssignmentAdapter(ctx, list);
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
        rvAssigment.setLayoutManager(mLayoutManager);
        rvAssigment.setAdapter(adapter);
    }
}

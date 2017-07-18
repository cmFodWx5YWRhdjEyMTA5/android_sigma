package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.HomeExpandableAdapter;
import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.ProjectResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/7/2017.
 */

public class HomeFragment extends BaseFragment {
    static Context ctx;
    private ApiService service;
    private ProudsApplication app;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(Context context) {
        HomeFragment fragment = new HomeFragment();
        ctx = context;
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void workingSpace(View view)
    {
        app = (ProudsApplication) ctx.getApplicationContext();
        RecyclerView rvHome = (RecyclerView) viewRoot.findViewById(R.id.rv_home);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvHome.setLayoutManager(layoutManager);

        HomeExpandableAdapter adapter = new HomeExpandableAdapter(getActivity(), generateList());
        adapter.setCustomParentAnimationViewId(R.id.iv_dropdown);
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        rvHome.setAdapter(adapter);
    }

    /* POPULATE LIST HERE */
    private ArrayList<ParentObject> generateList()
    {
        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        return parentObjects;
    }

    public void getData()
    {
        service = ApiUtils.apiService();
        service.getHome(app.getSessionManager().getToken()).enqueue(new Callback<ProjectResponse>()
        {
            @Override
            public void onResponse(Call<ProjectResponse> call, Response<ProjectResponse> response)
            {

            }

            @Override
            public void onFailure(Call<ProjectResponse> call, Throwable t)
            {

            }
        });

    }

}

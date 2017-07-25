package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.TeamMemberAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.TeamMemberResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/15/2017.
 */

public class TeamMemberFragment extends BaseFragment
{
    private ApiService service;
    static Context ctx;
    private String projectId;
    private ProudsApplication app;
    private RecyclerView rvTeamMember;
    private TeamMemberAdapter adapter;

    public static TeamMemberFragment newInstance(Context context, String projectId)
    {
        TeamMemberFragment fragment = new TeamMemberFragment();
        ctx = context;
        Bundle args = new Bundle();
        args.putString("project_id", projectId);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_team_member;
    }

    @Override
    protected void workingSpace(View view)
    {
        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();
        projectId = getArguments().getString("project_id");
        rvTeamMember = (RecyclerView) view.findViewById(R.id.rv_team_member);
        getTeamMemberData();
    }

    public void getTeamMemberData()
    {
        service.getTeamMember(projectId, app.getSessionManager().getToken()).enqueue(new Callback<TeamMemberResponse>()
        {
            @Override
            public void onResponse(Call<TeamMemberResponse> call, Response<TeamMemberResponse> response)
            {
                adapter = new TeamMemberAdapter(ctx, response.body().getProjectMember());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
                rvTeamMember.setLayoutManager(mLayoutManager);
                rvTeamMember.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<TeamMemberResponse> call, Throwable t)
            {

            }
        });
    }
}

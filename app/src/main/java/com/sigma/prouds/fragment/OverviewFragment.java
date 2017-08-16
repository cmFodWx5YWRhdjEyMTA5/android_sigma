package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sigma.prouds.LoginActivity;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.DetailProjectResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/15/2017.
 */

public class OverviewFragment extends BaseFragment
{

    private ApiService service;
    static Context ctx;
    private String projectId;
    private ProudsApplication app;
    private ScrollView svOverview;
    private ProgressBar pbOverview;

    private TextView tvIwo;

    public static OverviewFragment newInstance(Context context, String projectId) {
        OverviewFragment fragment = new OverviewFragment();
        ctx = context;
        Bundle args = new Bundle();
        args.putString("project_id", projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_overview;
    }

    @Override
    protected void workingSpace(View view)
    {
        tvIwo = (TextView) view.findViewById(R.id.tv_iwo);
//        svOverview = (ScrollView) view.findViewById(R.id.sv_overview);
        pbOverview = (ProgressBar) view.findViewById(R.id.pb_overview);
        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();
        projectId = getArguments().getString("project_id");
        Log.i("Project id frg : ", getArguments().getString("project_id"));
        getProjectDetailData();
    }

    public void getProjectDetailData()
    {
        service.getDetailProject(projectId, app.getSessionManager().getToken()).enqueue(new Callback<DetailProjectResponse>()
        {
            @Override
            public void onResponse(Call<DetailProjectResponse> call, Response<DetailProjectResponse> response)
            {
                if (query != null)
                {
                    query.id(pbOverview).gone();
                    query.id(svOverview).visible();
                    setView(response.body());
                }

            }

            @Override
            public void onFailure(Call<DetailProjectResponse> call, Throwable t)
            {

            }
        });
    }

    public void setView(DetailProjectResponse model)
    {
        query.id(R.id.tv_iwo).text(model.getOverview().getIwo());
        query.id(R.id.tv_buo).text(model.getOverview().getBuOwner());
        query.id(R.id.tv_desc).text(model.getOverview().getDescription() + "");
        query.id(R.id.tv_ev).text(model.getProjectPerformanceIndex().getEv());
        query.id(R.id.tv_pv).text(model.getProjectPerformanceIndex().getPv());
        query.id(R.id.tv_ac).text(model.getProjectPerformanceIndex().getAc());
        query.id(R.id.tv_spi).text(model.getProjectPerformanceIndex().getSpi() + "");
        query.id(R.id.tv_cpi).text(model.getProjectPerformanceIndex().getCpi() + "");
    }
}

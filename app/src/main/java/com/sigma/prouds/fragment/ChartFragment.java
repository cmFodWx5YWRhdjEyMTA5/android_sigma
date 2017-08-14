package com.sigma.prouds.fragment;

import android.content.Context;
import android.view.View;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.model.PerformanceSendModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.MyPerformanceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lucgu.qolfiera on 8/13/2017.
 */

public class ChartFragment extends BaseFragment
{
    static Context ctx;
    private ApiService service;
    private ProudsApplication app;
    private PerformanceSendModel model;
    private DonutProgress pbEntry, pbUtilization;

    public static ChartFragment newInstance(Context context)
    {
        ChartFragment fragment = new ChartFragment();
        ctx = context;
        return fragment;
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_chart;
    }

    @Override
    protected void workingSpace(View view)
    {
        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();
        pbEntry = (DonutProgress) view.findViewById(R.id.pb_user_entry);
        pbUtilization = (DonutProgress) view.findViewById(R.id.pb_user_utilization);
        model = new PerformanceSendModel();
        model.setBulan("8");
        model.setTahun("2017");
        getDataPerformance();
    }

    public void getDataPerformance()
    {
        service.getMyPerfomance(app.getSessionManager().getToken(), model).enqueue(new Callback<MyPerformanceResponse>()
        {
            @Override
            public void onResponse(Call<MyPerformanceResponse> call, Response<MyPerformanceResponse> response)
            {
                float progressEntry = Float.parseFloat(response.body().getEntry());
                int finalProgressEntry = (int) progressEntry;
                pbEntry.setProgress(finalProgressEntry);

                float progressUtil = Float.parseFloat(response.body().getEntry());
                int finalProgressUtil = (int) progressUtil;
                pbUtilization.setProgress(finalProgressUtil);
            }

            @Override
            public void onFailure(Call<MyPerformanceResponse> call, Throwable t)
            {

            }
        });
    }
}

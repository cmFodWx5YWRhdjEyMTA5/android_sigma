package com.sigma.prouds.fragment;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.model.PerformanceSendModel;
import com.sigma.prouds.model.PerformanceYearSendModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.MyPerformanceResponse;
import com.sigma.prouds.network.response.MyPerformanceYearlyResponse;

import java.util.ArrayList;
import java.util.List;

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
    private LineChart chartUtilization, chartEntry;

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
        chartUtilization = (LineChart) view.findViewById(R.id.chart_utilization);
        chartEntry = (LineChart) view.findViewById(R.id.chart_entry);
        model = new PerformanceSendModel();
        model.setBulan("8");
        model.setTahun("2017");
        getDataPerformance();
        getChartUtilziationData();
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

    public void getChartUtilziationData()
    {
        Log.i("Calling", "Req");
        PerformanceYearSendModel model = new PerformanceYearSendModel();
        model.setTahun("2017");
        Log.i("Calling2", "Req");
        service.getYearPerformance(app.getSessionManager().getToken(), model).enqueue(new Callback<MyPerformanceYearlyResponse>() {
            @Override
            public void onResponse(Call<MyPerformanceYearlyResponse> call, Response<MyPerformanceYearlyResponse> response)
            {
                Log.i("Response", response.body().getAllhour().get(0).getLabel());
                ArrayList<Entry> utilValue = new ArrayList<>();
                ArrayList<String> utilLabel = new ArrayList<String>();
                ArrayList<Entry> entryValue = new ArrayList<>();
                ArrayList<String> entryLabel = new ArrayList<String>();
                for (int i=0; i<= response.body().getAllhour().size()-1;i++)
                {
                    utilValue.add(new Entry(Float.parseFloat(response.body().getAllhour().get(i).getValue()), i));
                    utilLabel.add(response.body().getAllhour().get(i).getLabel().substring(0,3));

                    entryValue.add(new Entry(Float.parseFloat(response.body().getAllenrty().get(i).getValue()), i));
                    entryLabel.add(response.body().getAllenrty().get(i).getLabel().substring(0,3));
                }

                LineDataSet dataset = new LineDataSet(utilValue, "Utilization");
                LineData data = new LineData(utilLabel, dataset);

                LineDataSet datasetEntry = new LineDataSet(entryValue, "Entry");
                LineData dataEntry = new LineData(entryLabel, datasetEntry);

                chartUtilization.setData(data);
                chartUtilization.getXAxis().setLabelsToSkip(0);

                chartEntry.setData(dataEntry);
                chartEntry.getXAxis().setLabelsToSkip(0);

            }

            @Override
            public void onFailure(Call<MyPerformanceYearlyResponse> call, Throwable t)
            {

            }
        });
        Log.i("Calling3", "Req");
    }
}

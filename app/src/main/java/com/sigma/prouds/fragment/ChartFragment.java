package com.sigma.prouds.fragment;

import android.content.Context;
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
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.MyPerformanceResponse;

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
    private LineChart chartUtilization;

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
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(12f, 3));
        entries.add(new Entry(18f, 4));
        entries.add(new Entry(9f, 5));
        entries.add(new Entry(4f, 6));
        entries.add(new Entry(8f, 7));
        entries.add(new Entry(6f, 8));
        entries.add(new Entry(12f, 9));
        entries.add(new Entry(18f, 10));
        entries.add(new Entry(9f, 11));

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");
        labels.add("Jun");
        labels.add("Jul");
        labels.add("Agu");
        labels.add("Sep");
        labels.add("Oct");
        labels.add("Nov");
        labels.add("Dec");

        LineDataSet dataset = new LineDataSet(entries, "Utilization");
        LineData data = new LineData(labels, dataset);
        chartUtilization.setData(data);

        chartUtilization.getXAxis().setLabelsToSkip(0);


    }
}

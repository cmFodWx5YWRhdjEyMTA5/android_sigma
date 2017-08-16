package com.sigma.prouds.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;

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
import com.sigma.prouds.AddTimesheetActivity;
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
import java.util.Calendar;
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
    private EditText etChartTsMonth, etChartTsYear, etChartUtil, etChartEntry;
    private ProgressBar pbChartUtil, pbChartEntry;

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
        etChartTsMonth = (EditText) view.findViewById(R.id.et_chart_tsmonth);
        etChartTsYear = (EditText) view.findViewById(R.id.et_chart_tsyear);
        etChartUtil = (EditText) view.findViewById(R.id.et_chart_utilyear);
        etChartEntry = (EditText) view.findViewById(R.id.et_chart_entryyear);
        pbChartUtil = (ProgressBar) view.findViewById(R.id.pb_chart_util);
        pbChartEntry = (ProgressBar) view.findViewById(R.id.pb_chart_entry);

        model = new PerformanceSendModel();
        model.setBulan("8");
        model.setTahun("2017");
        getDataPerformance();

        //chart util
        Calendar calendar = Calendar.getInstance();
        etChartUtil.setText(calendar.get(Calendar.YEAR) + "");
        etChartEntry.setText(calendar.get(Calendar.YEAR) + "");
        getChartUtilziationData(calendar.get(Calendar.YEAR) + "");
        getChartEntryData(calendar.get(Calendar.YEAR) + "");

        etChartUtil.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(ctx);
                builderSingle.setTitle("Select year");
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ctx, android.R.layout.select_dialog_singlechoice);
                for (int i=1990; i <= 2030; i++)
                {
                    arrayAdapter.add(i + "");
                }
                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        etChartUtil.setText(arrayAdapter.getItem(position));
                        getChartUtilziationData(arrayAdapter.getItem(position));
                        dialog.dismiss();
                    }
                });
                builderSingle.show();
            }
        });

        etChartEntry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(ctx);
                builderSingle.setTitle("Select year");
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ctx, android.R.layout.select_dialog_singlechoice);
                for (int i=1990; i <= 2030; i++)
                {
                    arrayAdapter.add(i + "");
                }
                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        etChartEntry.setText(arrayAdapter.getItem(position));
                        getChartEntryData(arrayAdapter.getItem(position));
                        dialog.dismiss();
                    }
                });
                builderSingle.show();
            }
        });
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

    public void getChartUtilziationData(String year)
    {
        chartUtilization.setVisibility(View.INVISIBLE);
        pbChartUtil.setVisibility(View.VISIBLE);
        Log.i("Calling", "Req");
        PerformanceYearSendModel model = new PerformanceYearSendModel();
        model.setTahun(year);
        Log.i("Calling2", "Req");
        service.getYearPerformance(app.getSessionManager().getToken(), model).enqueue(new Callback<MyPerformanceYearlyResponse>() {
            @Override
            public void onResponse(Call<MyPerformanceYearlyResponse> call, Response<MyPerformanceYearlyResponse> response)
            {
                Log.i("Response", response.body().getAllhour().get(0).getLabel());
                ArrayList<Entry> utilValue = new ArrayList<>();
                ArrayList<String> utilLabel = new ArrayList<String>();
                for (int i=0; i<= response.body().getAllhour().size()-1;i++)
                {
                    utilValue.add(new Entry(Float.parseFloat(response.body().getAllhour().get(i).getValue()), i));
                    utilLabel.add(response.body().getAllhour().get(i).getLabel().substring(0,3));
                }

                LineDataSet dataset = new LineDataSet(utilValue, "Utilization");
                LineData data = new LineData(utilLabel, dataset);

                chartUtilization.setData(data);
                chartUtilization.getXAxis().setLabelsToSkip(0);

                pbChartUtil.setVisibility(View.GONE);
                chartUtilization.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<MyPerformanceYearlyResponse> call, Throwable t)
            {

            }
        });
    }

    public void getChartEntryData(String year)
    {
        chartEntry.setVisibility(View.INVISIBLE);
        pbChartEntry.setVisibility(View.VISIBLE);
        PerformanceYearSendModel model = new PerformanceYearSendModel();
        model.setTahun(year);
        service.getYearPerformance(app.getSessionManager().getToken(), model).enqueue(new Callback<MyPerformanceYearlyResponse>() {
            @Override
            public void onResponse(Call<MyPerformanceYearlyResponse> call, Response<MyPerformanceYearlyResponse> response)
            {
                ArrayList<Entry> entryValue = new ArrayList<>();
                ArrayList<String> entryLabel = new ArrayList<String>();
                for (int i=0; i<= response.body().getAllhour().size()-1;i++)
                {
                    entryValue.add(new Entry(Float.parseFloat(response.body().getAllenrty().get(i).getValue()), i));
                    entryLabel.add(response.body().getAllenrty().get(i).getLabel().substring(0,3));
                }

                LineDataSet datasetEntry = new LineDataSet(entryValue, "Entry");
                LineData dataEntry = new LineData(entryLabel, datasetEntry);

                chartEntry.setData(dataEntry);
                chartEntry.getXAxis().setLabelsToSkip(0);

                pbChartEntry.setVisibility(View.GONE);
                chartEntry.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<MyPerformanceYearlyResponse> call, Throwable t)
            {

            }
        });
    }
}

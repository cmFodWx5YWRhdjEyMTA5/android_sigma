package com.sigma.prouds.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.MyAssignmentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/7/2017.
 */

public class AssignmentFragment extends Fragment {

    static Context ctx;
    private ApiService service;
    private ProgressDialog dialog;

    public AssignmentFragment() {
        // Required empty public constructor
    }

    public static AssignmentFragment newInstance(Context context) {
        AssignmentFragment fragment = new AssignmentFragment();
        ctx = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        dialog = new ProgressDialog(ctx);
        dialog.setMessage("Please wait...");
        dialog.show();
        getAssignmentData();
        return inflater.inflate(R.layout.fragment_assignment, container, false);
    }

    public void getAssignmentData()
    {
        service = ApiUtils.apiService();
        service.getMyAssignmentResponse().enqueue(new Callback<MyAssignmentResponse>()
        {
            @Override
            public void onResponse(Call<MyAssignmentResponse> call, Response<MyAssignmentResponse> response)
            {
                dialog.dismiss();
                Log.i("Response", response.body().getAssignment().get(0).getProjectName().toString());
            }

            @Override
            public void onFailure(Call<MyAssignmentResponse> call, Throwable t)
            {
                dialog.dismiss();
                Log.i("Response", "Failed : " + t.toString());
            }
        });
    }
}

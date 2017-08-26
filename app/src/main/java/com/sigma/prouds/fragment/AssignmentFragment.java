package com.sigma.prouds.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sigma.prouds.AssignmentDetailsActivity;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.SearchAssignmentActivity;
import com.sigma.prouds.adapter.AssignmentAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.model.ProjectAssignmentModel;
import com.sigma.prouds.model.ProjectAssignmentNewModel;
import com.sigma.prouds.model.ProjectDetailModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.MyAssignmentNewResponse;
import com.sigma.prouds.network.response.MyAssignmentResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
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
    private LinearLayout llSearch;
    private AssignmentAdapter adapter;
    private List<ProjectDetailModel> listItem;
    private EditText etSearch;

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
        llSearch = (LinearLayout) view.findViewById(R.id.ll_search);
        etSearch = (EditText) view.findViewById(R.id.et_search);
        getAssignmentData();

        etSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", (Serializable) listItem);
                Intent intent = new Intent(getActivity(), SearchAssignmentActivity.class);
                intent.putExtra(SearchAssignmentActivity.KEY_SEARCH_ASSIGNMENT, bundle);
                startActivity(intent);
            }
        });
    }

    public void getAssignmentData()
    {
        llSearch.setVisibility(View.GONE);
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
                        listItem.add(response.body().getAssignment().get(i).getProjectDetail().get(j));
                    }
                }
                setAdapterView(listItem);
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
        llSearch.setVisibility(View.VISIBLE);
    }

    public void onEvent(ProjectDetailModel model)
    {
        Log.i("Data", model.getAssignmentList().get(0).getProjectName());
        Intent intent = new Intent(getActivity(), AssignmentDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", model);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }
}

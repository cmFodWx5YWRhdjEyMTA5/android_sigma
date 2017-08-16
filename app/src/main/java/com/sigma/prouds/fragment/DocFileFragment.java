package com.sigma.prouds.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.sigma.prouds.FormDocUploadActivity;
import com.sigma.prouds.FormReportIssueActivity;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.ProjectDocAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.model.ProjectDocModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.ProjectDocResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/15/2017.
 */

public class DocFileFragment extends BaseFragment
{
    private ApiService service;
    static Context ctx;
    private String projectId;
    private ProudsApplication app;
    private RecyclerView rvDoc;
    private ProjectDocAdapter adapter;
    private RelativeLayout rlAddDoc;

    public static DocFileFragment newInstance(Context context, String projectId)
    {
        DocFileFragment fragment = new DocFileFragment();
        ctx = context;
        Bundle args = new Bundle();
        args.putString("project_id", projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_docfile;
    }

    @Override
    protected void workingSpace(View view)
    {
        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();
        projectId = getArguments().getString("project_id");
        rvDoc = (RecyclerView) view.findViewById(R.id.rv_doc);
        rlAddDoc = (RelativeLayout) view.findViewById(R.id.rl_doc);
        rlAddDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddDoc();
            }
        });
        getDocFile();

    }

    public void getDocFile()
    {
        service.getProjectDoc(projectId, app.getSessionManager().getToken()).enqueue(new Callback<ProjectDocResponse>()
        {
            @Override
            public void onResponse(Call<ProjectDocResponse> call, Response<ProjectDocResponse> response)
            {
                adapter = new ProjectDocAdapter(ctx, response.body().getProjectDocList());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
                rvDoc.setLayoutManager(mLayoutManager);
                rvDoc.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ProjectDocResponse> call, Throwable t)
            {

            }
        });
    }

    public void toAddDoc()
    {
        Intent intent = new Intent(getActivity(), FormDocUploadActivity.class);
        intent.putExtra("project_id", projectId);
        getActivity().startActivity(intent);
    }
}

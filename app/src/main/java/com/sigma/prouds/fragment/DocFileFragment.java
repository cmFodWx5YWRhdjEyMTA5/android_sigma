package com.sigma.prouds.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private ProgressBar pbDocFile;
    private TextView tvEmptyDocument;

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
        pbDocFile = (ProgressBar) view.findViewById(R.id.pb_doc_file);
        tvEmptyDocument = (TextView) view.findViewById(R.id.tv_empty_document);
        query.id(R.id.tv_empty_document).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_doc_upload).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
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
        pbDocFile.setVisibility(View.VISIBLE);
        tvEmptyDocument.setVisibility(View.GONE);
        service.getProjectDoc(projectId, app.getSessionManager().getToken()).enqueue(new Callback<ProjectDocResponse>()
        {
            @Override
            public void onResponse(Call<ProjectDocResponse> call, Response<ProjectDocResponse> response)
            {
                adapter = new ProjectDocAdapter(ctx, response.body().getProjectDocList());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
                rvDoc.setLayoutManager(mLayoutManager);
                rvDoc.setAdapter(adapter);
                pbDocFile.setVisibility(View.GONE);

                if (response.body().getProjectDocList().size() == 0)
                {
                    tvEmptyDocument.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ProjectDocResponse> call, Throwable t)
            {
                pbDocFile.setVisibility(View.GONE);
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

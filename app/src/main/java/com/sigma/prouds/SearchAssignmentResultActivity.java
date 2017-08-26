package com.sigma.prouds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sigma.prouds.adapter.AssignmentDetailsAdapter;
import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.ProjectAssignmentModel;
import com.sigma.prouds.model.ProjectDetailModel;

import java.util.ArrayList;

public class SearchAssignmentResultActivity extends BaseActivity {

    private RecyclerView rvSearch;
    private AssignmentDetailsAdapter adapter;
    private String searchResult;
    private EditText etSearch;
    private TextView tvNotFound;
    private ArrayList<ProjectDetailModel> list;
    private int position;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_search_assignment_result;
    }

    @Override
    protected void workingSpace()
    {
        assignXML();

        list = new ArrayList<>();

        searchResult = getIntent().getExtras().getString("search_result");
        Bundle bundle = getIntent().getExtras().getBundle("search_bundle");
        list = (ArrayList<ProjectDetailModel>) bundle.getSerializable("search");
        position = bundle.getInt("search_position");

        if (position != -1)
        {
            tvNotFound.setVisibility(View.GONE);
            etSearch.setText(searchResult);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            rvSearch.setLayoutManager(layoutManager);
            adapter = new AssignmentDetailsAdapter(list.get(position).getAssignmentList(), getApplicationContext());
            rvSearch.setAdapter(adapter);
        }
        else if (position == -1)
        {
            tvNotFound.setVisibility(View.VISIBLE);
        }

    }

    public void assignXML()
    {
        rvSearch = (RecyclerView) findViewById(R.id.rv_search_assignment);
        etSearch = (EditText) findViewById(R.id.et_search_assignment_result);
        tvNotFound = (TextView) findViewById(R.id.tv_not_found_assignment);
    }

    public void onEvent(ProjectAssignmentModel model)
    {
        Intent intent = new Intent(this, AddTimesheetActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", model);
        intent.putExtra("model", bundle);
        startActivity(intent);
    }
}

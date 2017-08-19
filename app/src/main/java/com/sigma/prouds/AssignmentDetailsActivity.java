package com.sigma.prouds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.sigma.prouds.adapter.AssignmentAdapter;
import com.sigma.prouds.adapter.AssignmentDetailsAdapter;
import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.ProjectAssignmentModel;
import com.sigma.prouds.model.ProjectDetailModel;

import java.util.List;

public class AssignmentDetailsActivity extends BaseActivity
{
    private RecyclerView rvAssignmentDetails;
    private ProjectDetailModel model;
    private List<ProjectAssignmentModel> list;
    private AssignmentDetailsAdapter adapter;
    private TextView tvAssignment;

    @Override
    protected int getLayout() {
        return R.layout.activity_assignment_details;
    }

    @Override
    protected void workingSpace()
    {
        model = new ProjectDetailModel();
        Bundle bundle = new Bundle();
        bundle = getIntent().getBundleExtra("data");
        model = (ProjectDetailModel) bundle.getSerializable("data");
        list = model.getAssignmentList();
        Log.i("coba", model.getProjectName());
        rvAssignmentDetails = (RecyclerView) findViewById(R.id.rv_assignment_details);
        tvAssignment = (TextView) findViewById(R.id.tv_assignment_details);
        adapter = new AssignmentDetailsAdapter(model.getAssignmentList(), this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvAssignmentDetails.setLayoutManager(manager);
        rvAssignmentDetails.setAdapter(adapter);
        tvAssignment.setText(model.getProjectName());
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

package com.sigma.prouds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    @Override
    protected int getLayout() {
        return R.layout.activity_assignment_details;
    }

    @Override
    protected void workingSpace()
    {
        model = new ProjectDetailModel(null);
        model = getIntent().getParcelableExtra("data");
        list = model.getAssignmentList();
        //Log.i("coba", getIntent().getExtras().getParcelableArrayList("data").get(0).toString());
        rvAssignmentDetails = (RecyclerView) findViewById(R.id.rv_assignment_details);
        adapter = new AssignmentDetailsAdapter(list, this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvAssignmentDetails.setLayoutManager(manager);
        rvAssignmentDetails.setAdapter(adapter);
    }
}

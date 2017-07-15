package com.sigma.prouds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sigma.prouds.adapter.AssignmentAdapter;
import com.sigma.prouds.base.BaseActivity;

public class AssignmentDetailsActivity extends BaseActivity {
    private RecyclerView rvAssignmentDetails;

    @Override
    protected int getLayout() {
        return R.layout.activity_assignment_details;
    }

    @Override
    protected void workingSpace() {
        rvAssignmentDetails = (RecyclerView) findViewById(R.id.rv_assignment_details);
        // SET ADAPTER HERE
    }
}

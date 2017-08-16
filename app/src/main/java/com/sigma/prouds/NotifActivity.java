package com.sigma.prouds;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.adapter.NotifAdapter;
import com.sigma.prouds.base.BaseActivity;

/**
 * Created by goy on 7/7/2017.
 */

public class NotifActivity extends BaseActivity {

    private RecyclerView rvNotif;
    private NotifAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_notif;
    }

    @Override
    protected void workingSpace() {
        rvNotif = (RecyclerView) findViewById(R.id.rv_notif);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvNotif.setLayoutManager(layoutManager);

        adapter = new NotifAdapter();

        rvNotif.setAdapter(adapter);
    }


}

package com.sigma.prouds.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sigma.prouds.R;
import com.sigma.prouds.adapter.MyActivityAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.model.ProjectActivityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1414 on 8/7/2017.
 */

public class MyActivityFragment extends BaseFragment
{
    static Context ctx;
    private RecyclerView vpActivity;
    private MyActivityAdapter adapter;
    private List<ProjectActivityModel> list;




    public static MyActivityFragment newInstance(Context context)
    {
        MyActivityFragment fragment = new MyActivityFragment();
        ctx = context;
        return fragment;
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_my_activity;
    }

    @Override
    protected void workingSpace(View view)
    {
        vpActivity = (RecyclerView) view.findViewById(R.id.rv_my_activity);
        list = new ArrayList<>();
        for (int i = 0; i <= 10 ; i ++)
        {
            ProjectActivityModel model = new ProjectActivityModel();
            model.setMessage("item " + i);
            list.add(model);
        }


        adapter = new MyActivityAdapter(ctx, list);
        vpActivity.setLayoutManager(new LinearLayoutManager(ctx));
        vpActivity.setAdapter(adapter);
    }
}

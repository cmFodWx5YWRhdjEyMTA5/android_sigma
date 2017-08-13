package com.sigma.prouds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.holder.ActivityHolder;
import com.sigma.prouds.holder.MyActivityHolder;
import com.sigma.prouds.model.ProjectActivityModel;

import java.util.List;

/**
 * Created by 1414 on 8/7/2017.
 */

public class MyActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private Context context;
    private List<ProjectActivityModel> list;

    public MyActivityAdapter(Context context, List<ProjectActivityModel> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_activity, parent, false);
        return new ActivityHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        ActivityHolder item = (ActivityHolder) holder;
        item.bind(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}

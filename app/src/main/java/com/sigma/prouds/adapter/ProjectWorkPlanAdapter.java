package com.sigma.prouds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.holder.WorkplanHolder;
import com.sigma.prouds.model.ProjectWorkplanStatusModel;
import com.sigma.prouds.model.TaskModel;

import java.util.List;

/**
 * Created by lucgu.qolfiera on 8/12/2017.
 */

public class ProjectWorkPlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private List<TaskModel> list;

    public ProjectWorkPlanAdapter(Context context, List<TaskModel> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_workplan, parent, false);
        return new WorkplanHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        WorkplanHolder item = (WorkplanHolder) holder;
        item.bind(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}

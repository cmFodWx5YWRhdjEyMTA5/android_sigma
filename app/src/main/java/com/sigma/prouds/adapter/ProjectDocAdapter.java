package com.sigma.prouds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.holder.ProjectDocHolder;
import com.sigma.prouds.model.ProjectDocModel;

import java.util.List;

/**
 * Created by 1414 on 7/24/2017.
 */

public class ProjectDocAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private List<ProjectDocModel> list;
    private Context context;

    public ProjectDocAdapter(Context context, List<ProjectDocModel> list)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_docfile, parent, false);
        return new ProjectDocHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        ProjectDocHolder item = (ProjectDocHolder) holder;
        item.bind(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}

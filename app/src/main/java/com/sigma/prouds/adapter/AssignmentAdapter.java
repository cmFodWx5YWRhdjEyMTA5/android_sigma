package com.sigma.prouds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.holder.AssignmentHolder;
import com.sigma.prouds.model.ProjectAssignmentModel;

import java.util.List;

/**
 * Created by 1414 on 7/11/2017.
 */

public class AssignmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private List<ProjectAssignmentModel> list;
    private Context context;

    public AssignmentAdapter(Context context, List<ProjectAssignmentModel> list)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_assignment, parent, false);
        return new AssignmentHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        AssignmentHolder item = (AssignmentHolder) holder;
        item.bind(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}

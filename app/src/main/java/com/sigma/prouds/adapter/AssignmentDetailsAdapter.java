package com.sigma.prouds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.holder.AssignmentDetailHolder;
import com.sigma.prouds.model.ProjectAssignmentModel;
import com.sigma.prouds.model.ProjectDetailModel;

import java.util.List;

/**
 * Created by goy on 7/15/2017.
 */

public class AssignmentDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<ProjectAssignmentModel> model;
    private Context context;

    public AssignmentDetailsAdapter(List<ProjectAssignmentModel> model, Context context)
    {
        this.model = model;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_assignment_details, parent, false);
        return new AssignmentDetailHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        AssignmentDetailHolder item = (AssignmentDetailHolder) holder;
        item.bind(model.get(position));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}

package com.sigma.prouds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.holder.TeamMemberHolder;
import com.sigma.prouds.model.TeamModel;

import java.util.List;

/**
 * Created by 1414 on 7/24/2017.
 */

public class TeamMemberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private List<TeamModel> list;

    public TeamMemberAdapter(Context context, List<TeamModel> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_team_member, parent, false);
        return new TeamMemberHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        TeamMemberHolder item = (TeamMemberHolder) holder;
        item.bind(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}

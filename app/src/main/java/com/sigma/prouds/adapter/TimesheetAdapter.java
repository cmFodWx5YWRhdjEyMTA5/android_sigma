package com.sigma.prouds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.holder.TimesheetHolder;
import com.sigma.prouds.model.UserActivityTimesheetModel;

import java.util.List;

/**
 * Created by lucgu.qolfiera on 8/16/2017.
 */

public class TimesheetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private List<UserActivityTimesheetModel> list;

    public TimesheetAdapter(Context context, List<UserActivityTimesheetModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView =  LayoutInflater.from(parent.getContext())
            .inflate(R.layout.holder_timesheet, parent, false);
        return new TimesheetHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        TimesheetHolder item = (TimesheetHolder) holder;
        item.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

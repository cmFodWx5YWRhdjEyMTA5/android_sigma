package com.sigma.prouds.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.ProjectWorkplanStatusModel;
import com.sigma.prouds.model.TaskModel;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by lucgu.qolfiera on 8/12/2017.
 */

public class WorkplanChildHolder extends ChildViewHolder
{

    private TextView tvStartDate, tvEndDate, tvDuration, tvWork;

    public WorkplanChildHolder(Context context, View itemView)
    {
        super(itemView);
        tvStartDate = (TextView) itemView.findViewById(R.id.tv_wp_startdate);
        tvEndDate = (TextView) itemView.findViewById(R.id.tv_wp_enddate);
        tvDuration = (TextView) itemView.findViewById(R.id.tv_wp_duration);
        tvWork = (TextView) itemView.findViewById(R.id.tv_wp_work);
    }

    public void onBind(TaskModel model)
    {

    }
}

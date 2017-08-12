package com.sigma.prouds.holder;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by lucgu.qolfiera on 8/12/2017.
 */

public class WorkplanParentHolder extends GroupViewHolder
{
    private TextView tvTask, tvMonitoringPercent, tvMonitoringStatus;
    private ProgressBar pbMonitoring;

    public WorkplanParentHolder(Context context, View itemView)
    {
        super(itemView);
        tvTask = (TextView) itemView.findViewById(R.id.tv_workplan_parent_task);
        pbMonitoring = (ProgressBar) itemView.findViewById(R.id.pb_monitoring);
        tvMonitoringPercent = (TextView) itemView.findViewById(R.id.tv_monitoring_percen);
        tvMonitoringStatus = (TextView) itemView.findViewById(R.id.tv_monitoring_status);
    }

    public void bindTask(ExpandableGroup group)
    {

    }
}

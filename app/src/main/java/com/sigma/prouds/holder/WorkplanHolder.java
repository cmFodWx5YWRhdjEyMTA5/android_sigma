package com.sigma.prouds.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.sigma.prouds.R;
import com.sigma.prouds.model.TaskModel;

/**
 * Created by lucgu.qolfiera on 8/12/2017.
 */

public class WorkplanHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private TextView tvTask, tvMonitoringPercent, tvMonitoringStatus;
    private ProgressBar pbMonitoring;
    private ExpandableLinearLayout expandableLinearLayout;
    private ImageView ivToggle;

    public WorkplanHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        tvTask = (TextView) itemView.findViewById(R.id.tv_workplan_parent_task);
        pbMonitoring = (ProgressBar) itemView.findViewById(R.id.pb_monitoring);
        tvMonitoringPercent = (TextView) itemView.findViewById(R.id.tv_monitoring_percen);
        tvMonitoringStatus = (TextView) itemView.findViewById(R.id.tv_monitoring_status);
        expandableLinearLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.ex_layout);
        ivToggle = (ImageView) itemView.findViewById(R.id.iv_wp_dropdown);
    }

    public void bind(TaskModel model)
    {
        tvTask.setText(model.getWbsName());
        tvMonitoringStatus.setText(model.getProjectStatus());

        ivToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLinearLayout.toggle();
            }
        });
    }
}

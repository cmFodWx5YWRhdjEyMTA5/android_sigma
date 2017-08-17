package com.sigma.prouds.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.ProjectAssignmentModel;

/**
 * Created by lucgu.qolfiera on 8/17/2017.
 */

public class AssignmentDetailHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private TextView tvTask, tvStartDate, tvEndDate;
    private ImageView ivAddTimesheet;

    public AssignmentDetailHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        tvTask = (TextView) itemView.findViewById(R.id.tv_task);
        tvStartDate = (TextView) itemView.findViewById(R.id.tv_ad_startdate);
        tvEndDate = (TextView) itemView.findViewById(R.id.tv_ad_enddate);
        ivAddTimesheet = (ImageView) itemView.findViewById(R.id.iv_add_timesheet);

    }

    public void bind(ProjectAssignmentModel model)
    {
        tvTask.setText(model.getWbsName());
        tvStartDate.setText(model.getStartDate());
        tvEndDate.setText(model.getFinishDate());
    }
}

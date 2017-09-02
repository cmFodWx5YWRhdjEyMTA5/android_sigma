package com.sigma.prouds.holder;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.ProjectAssignmentModel;

import de.greenrobot.event.EventBus;

/**
 * Created by lucgu.qolfiera on 8/17/2017.
 */

public class AssignmentDetailHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private TextView tvTask, tvStartDate, tvEndDate, tvDue, tvTitleStartDate, tvTitleEndDate, tvStatus, tvPercent;
    private ImageView ivAddTimesheet;
    private ProgressBar pbTask;
    private Typeface latoBold, latoRegular;

    public AssignmentDetailHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        tvTask = (TextView) itemView.findViewById(R.id.tv_task);
        tvStartDate = (TextView) itemView.findViewById(R.id.tv_ad_startdate);
        tvEndDate = (TextView) itemView.findViewById(R.id.tv_ad_enddate);
        tvTitleStartDate = (TextView) itemView.findViewById(R.id.tv_ad_title_startdate);
        tvTitleEndDate = (TextView) itemView.findViewById(R.id.tv_ad_title_enddate);
        tvDue = (TextView) itemView.findViewById(R.id.tv_ad_due);
        ivAddTimesheet = (ImageView) itemView.findViewById(R.id.iv_add_timesheet);
        tvStatus = (TextView) itemView.findViewById(R.id.tv_assignment_details_status);
        pbTask = (ProgressBar) itemView.findViewById(R.id.pb_detail_assignment);
        tvPercent = (TextView) itemView.findViewById(R.id.tv_percen_detail_assignment);
        latoBold = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_bold.ttf");
        latoRegular = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_regular.ttf");

    }

    public void bind(final ProjectAssignmentModel model)
    {
        tvTask.setText(model.getWbsName());
        tvStartDate.setText(model.getStartDate());
        tvEndDate.setText(model.getFinishDate());
        ivAddTimesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(model);
            }
        });
        tvStatus.setText(model.getProjectStatus());
        tvPercent.setText(model.getProjectPercent() + "%");

        try
        {
            float progress = Float.parseFloat(model.getProjectPercent());
            int finalProgress = (int) progress;
            pbTask.setProgress(finalProgress);
        }
        catch (Exception e)
        {

        }

        setTypeFace();
    }

    public void setTypeFace() {
        tvTask.setTypeface(latoRegular);
        tvStartDate.setTypeface(latoRegular);
        tvEndDate.setTypeface(latoRegular);
        tvTitleStartDate.setTypeface(latoBold);
        tvTitleEndDate.setTypeface(latoBold);
        tvDue.setTypeface(latoRegular);
        tvStatus.setTypeface(latoRegular);
        tvPercent.setTypeface(latoRegular);
    }
}

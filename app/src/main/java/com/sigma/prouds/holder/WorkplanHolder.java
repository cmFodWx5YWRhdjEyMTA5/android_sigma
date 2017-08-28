package com.sigma.prouds.holder;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.sigma.prouds.R;
import com.sigma.prouds.model.TaskModel;
import com.sigma.prouds.model.WorkplanNewModel;

/**
 * Created by lucgu.qolfiera on 8/12/2017.
 */

public class WorkplanHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private TextView tvTask, tvMonitoringPercent, tvMonitoringStatus, tvSeeAll, tvStartDateTitle,
            tvStartDate, tvDurationTitle, tvDuration, tvEndDateTitle, tvEndDate, tvWorkTitle, tvWork;
    private ProgressBar pbMonitoring;
    private ExpandableLinearLayout expandableLinearLayout;
    private ImageView ivToggle;
    private Typeface latoBold, latoBlack, latoRegular;

    public WorkplanHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        tvTask = (TextView) itemView.findViewById(R.id.tv_workplan_parent_task);
        tvMonitoringPercent = (TextView) itemView.findViewById(R.id.tv_monitoring_percen);
        tvMonitoringStatus = (TextView) itemView.findViewById(R.id.tv_monitoring_status);
        tvSeeAll = (TextView) itemView.findViewById(R.id.tv_seeall);
        tvStartDateTitle = (TextView) itemView.findViewById(R.id.tv_wp_title_startdate);
        tvStartDate = (TextView) itemView.findViewById(R.id.tv_wp_startdate);
        tvDurationTitle = (TextView) itemView.findViewById(R.id.tv_wp_title_duration);
        tvDuration = (TextView) itemView.findViewById(R.id.tv_wp_duration);
        tvEndDateTitle = (TextView) itemView.findViewById(R.id.tv_wp_title_enddate);
        tvEndDate = (TextView) itemView.findViewById(R.id.tv_wp_enddate);
        tvWorkTitle = (TextView) itemView.findViewById(R.id.tv_wp_title_work);
        tvWork = (TextView) itemView.findViewById(R.id.tv_wp_work);
        expandableLinearLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.ex_layout);
        pbMonitoring = (ProgressBar) itemView.findViewById(R.id.pb_monitoring);
        ivToggle = (ImageView) itemView.findViewById(R.id.iv_wp_dropdown);

        latoBold = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_bold.ttf");
        latoBlack = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_black.ttf");
        latoRegular = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_regular.ttf");
    }

    public void bind(WorkplanNewModel model)
    {
        tvTask.setText(model.getWbsName());
        tvStartDate.setText(model.getStartDate());
        tvEndDate.setText(model.getFinishDate());
        tvWork.setText(model.getWork());
        tvDuration.setText(model.getDuration());
        tvMonitoringPercent.setText(model.getWorkPercentageComplete() + "%");
        tvMonitoringStatus.setText(model.getProjectStatus());
        //tvMonitoringStatus.setText(model.getProjectStatus());
        ivToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLinearLayout.toggle();
            }
        });
        setTypeFace();

        try
        {
            float progress = Float.parseFloat(model.getWorkPercentageComplete());
            int finalProgress = (int) progress;
            pbMonitoring.setProgress(finalProgress);
        }
        catch (Exception e)
        {

        }

    }

    public void setTypeFace() {
        tvTask.setTypeface(latoRegular);
        tvMonitoringPercent.setTypeface(latoBlack);
        tvMonitoringStatus.setTypeface(latoRegular);
        tvSeeAll.setTypeface(latoBold);
        tvStartDateTitle.setTypeface(latoBold);
        tvStartDate.setTypeface(latoRegular);
        tvDurationTitle.setTypeface(latoBold);
        tvDuration.setTypeface(latoRegular);
        tvEndDateTitle.setTypeface(latoBold);
        tvEndDate.setTypeface(latoRegular);
        tvWorkTitle.setTypeface(latoBold);
        tvWork.setTypeface(latoRegular);
    }
}

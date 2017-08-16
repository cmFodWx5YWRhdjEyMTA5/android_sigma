package com.sigma.prouds.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.UserActivityTimesheetModel;
import com.sigma.prouds.network.response.UserProjectTimesheetResponse;

/**
 * Created by lucgu.qolfiera on 8/16/2017.
 */

public class TimesheetHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private TextView tvTsTime;
    private TextView tvTsSubmit;
    private TextView tvTsSubject;
    private TextView tvDesc;

    public TimesheetHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        tvTsTime = (TextView) itemView.findViewById(R.id.tv_ts_time);
        tvTsSubmit = (TextView) itemView.findViewById(R.id.tv_ts_submit);
        tvTsSubject = (TextView) itemView.findViewById(R.id.tv_ts_errormsg);
        tvDesc = (TextView) itemView.findViewById(R.id.tv_act_desc);

    }

    public void bind(UserActivityTimesheetModel model)
    {
        tvTsTime.setText(model.getTsDate());
        tvTsSubject.setText(model.getSubject());
        tvTsSubmit.setText("Submitted " + model.getHourTotal() + " hour work for " + model.getWbsName() + " in" + model.getProjectName());
        tvDesc.setText(model.getMessage());
    }
}

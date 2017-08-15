package com.sigma.prouds.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sigma.prouds.model.UserActivityTimesheetModel;
import com.sigma.prouds.network.response.UserProjectTimesheetResponse;

/**
 * Created by lucgu.qolfiera on 8/16/2017.
 */

public class TimesheetHolder extends RecyclerView.ViewHolder
{
    private Context context;

    public TimesheetHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
    }

    public void bind(UserActivityTimesheetModel model)
    {

    }
}

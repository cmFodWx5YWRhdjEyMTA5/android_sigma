package com.sigma.prouds.holder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sigma.prouds.CustomTypefaceSpan;
import com.sigma.prouds.R;
import com.sigma.prouds.model.UserActivityTimesheetModel;
import com.sigma.prouds.network.response.UserProjectTimesheetResponse;

import de.greenrobot.event.EventBus;

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
    private TextView tvIsAproved;
    private TextView tvReSubmit;
    private Typeface latoBold, latoRegular;
    private RelativeLayout rlDenied, rlApproved, rlWait;
    String hour, projectName, wbs;


    public TimesheetHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        tvIsAproved = (TextView) itemView.findViewById(R.id.tv_ts_isapproved);
        tvTsTime = (TextView) itemView.findViewById(R.id.tv_ts_time);
        tvTsSubmit = (TextView) itemView.findViewById(R.id.tv_ts_submit);
        tvTsSubject = (TextView) itemView.findViewById(R.id.tv_ts_errormsg);
        tvDesc = (TextView) itemView.findViewById(R.id.tv_act_desc);
        tvReSubmit = (TextView) itemView.findViewById(R.id.tv_ts_resubmit);
        rlDenied = (RelativeLayout) itemView.findViewById(R.id.rl_ts_status_denied);
        rlApproved = (RelativeLayout) itemView.findViewById(R.id.rl_ts_status_approved);
        rlWait = (RelativeLayout) itemView.findViewById(R.id.rl_ts_status_wait);

        latoBold = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_bold.ttf");
        latoRegular = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_regular.ttf");
    }

    public void bind(final UserActivityTimesheetModel model)
    {
        tvTsTime.setText(model.getTsDate());
        tvTsTime.setTypeface(latoRegular);

        tvTsSubject.setText(model.getSubject());
        tvTsSubject.setTypeface(latoRegular);

        if (model.getHourTotal() != null) {
            hour = model.getHourTotal();
        }
        else {
            hour = "(not set)";
        }
        if (model.getProjectName() != null) {
            projectName = model.getProjectName();
        }
        else {
            projectName = "(not set)";
        }
        if (model.getWbsName() != null) {
            wbs = "for " + model.getWbsName();
        }
        else {
            wbs = "(not set)";
        }
        String submit = "Submitted " ;
        String hoursWork = " hours work ";
        String in = " in ";

        Spannable spannable = new SpannableString(submit + hour + hoursWork + wbs + in + projectName);
        spannable.setSpan( new CustomTypefaceSpan("sans", latoRegular),
                0,
                submit.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan( new CustomTypefaceSpan("sans", latoBold),
                submit.length(),
                submit.length() + hour.length() + hoursWork.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan( new CustomTypefaceSpan("sans", latoRegular),
                submit.length() + hour.length() + hoursWork.length(),
                submit.length() + hour.length() + hoursWork.length() + wbs.length() + in.length() + projectName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan( new ForegroundColorSpan(Color.parseColor("#fa5962")),
                submit.length() + hour.length() + hoursWork.length() + wbs.length() + in.length(),
                submit.length() + hour.length() + hoursWork.length() + wbs.length() + in.length() + projectName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTsSubmit.setText(spannable);

        tvDesc.setText(model.getMessage());
        tvDesc.setTypeface(latoRegular);

        if (model.getIsApproved().equalsIgnoreCase("0"))
        {
            tvReSubmit.setVisibility(View.VISIBLE);
//            tvIsAproved.setText("Denied");
            rlDenied.setVisibility(View.VISIBLE);
            rlApproved.setVisibility(View.GONE);
            rlWait.setVisibility(View.GONE);
        }
        else if (model.getIsApproved().equalsIgnoreCase("1"))
        {
            tvReSubmit.setVisibility(View.GONE);
//            tvIsAproved.setText("Approved");
            rlDenied.setVisibility(View.GONE);
            rlApproved.setVisibility(View.VISIBLE);
            rlWait.setVisibility(View.GONE);
        }
        else if (model.getIsApproved().equalsIgnoreCase("-1"))
        {
            tvReSubmit.setVisibility(View.GONE);
//            tvIsAproved.setText("Waiting for approval");
            rlDenied.setVisibility(View.GONE);
            rlApproved.setVisibility(View.GONE);
            rlWait.setVisibility(View.VISIBLE);
        }

        tvReSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EventBus.getDefault().post(model);
            }
        });

    }

}

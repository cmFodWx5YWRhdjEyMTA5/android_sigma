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
import android.widget.TextView;

import com.sigma.prouds.CustomTypefaceSpan;
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
    private TextView tvIsAproved;
    private TextView tvReSubmit;
    private Typeface latoBold, latoRegular;


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

        latoBold = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_bold.ttf");
        latoRegular = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_regular.ttf");
    }

    public void bind(UserActivityTimesheetModel model)
    {
        tvTsTime.setText(model.getTsDate());
        tvTsTime.setTypeface(latoRegular);

        tvTsSubject.setText(model.getSubject());
        tvTsSubject.setTypeface(latoRegular);

        String submit = "Submitted " ;
        String hour = model.getHourTotal();
        String hoursWork = " hours work ";
        String wbs = "for " + model.getWbsName();
        String in = " in ";
        String projectName = model.getProjectName();
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
    }

}

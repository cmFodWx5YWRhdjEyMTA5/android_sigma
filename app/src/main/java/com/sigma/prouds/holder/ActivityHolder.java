package com.sigma.prouds.holder;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sigma.prouds.CustomTypefaceSpan;
import com.sigma.prouds.R;
import com.sigma.prouds.fragment.MyActivityFragment;
import com.sigma.prouds.model.ProjectActivityModel;

import de.greenrobot.event.EventBus;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lucgu.qolfiera on 8/12/2017.
 */

public class ActivityHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private CircleImageView ivProfile;
    private TextView tvUser, tvPosition, tvProjectName, tvActName, tvSubject, tvDesc, tvFooter, tvApprove, tvDenied, tvWait, tvReSubmit;
    private Typeface latoBold, latoBlack, latoRegular;
    private String errorMsg;

    public ActivityHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        ivProfile = (CircleImageView) itemView.findViewById(R.id.civ_activity);
        tvUser = (TextView) itemView.findViewById(R.id.tv_act_user);
        tvPosition = (TextView) itemView.findViewById(R.id.tv_act_position);
        tvProjectName = (TextView) itemView.findViewById(R.id.tv_act_project_name);
        tvActName = (TextView) itemView.findViewById(R.id.tv_act_name);
        tvSubject = (TextView) itemView.findViewById(R.id.tv_act_errormsg);
        tvDesc = (TextView) itemView.findViewById(R.id.tv_act_desc);
        tvFooter = (TextView) itemView.findViewById(R.id.tv_act_footer);
        tvApprove = (TextView) itemView.findViewById(R.id.tv_act_approve);
        tvDenied = (TextView) itemView.findViewById(R.id.tv_act_deny);
        tvWait = (TextView) itemView.findViewById(R.id.tv_act_wait_approval);
        tvReSubmit = (TextView) itemView.findViewById(R.id.tv_act_resubmit);

        latoBold = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_bold.ttf");
        latoBlack = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_black.ttf");
        latoRegular = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_regular.ttf");
    }

    public void bind(final ProjectActivityModel model)
    {
        tvUser.setText(model.getUserName());
        tvActName.setText(model.getWbsName());
        tvDesc.setText(model.getMessage());

        // set multiple font color
        String project = "<font color=#3a424c>Project </font>";
        String projectName = model.getProjectName();
        if (Build.VERSION.SDK_INT >= 24) {
            Log.i("textStyle", "24");
            tvProjectName.setText(Html.fromHtml(project+projectName, 0)); // for 24 api and more
        } else {
            Log.i("textStyle", "below 24");
            tvProjectName.setText(Html.fromHtml(project+projectName));// or for older api
        }

        // set multiple font style
        if (model.getWbsName() != null) {
            errorMsg = model.getSubject();
        }
        else {
            errorMsg = "";
        }
        String errorMsgBefore = "left a ";
        String errorMsgAfter = " message";
        Spannable spannable = new SpannableString(errorMsgBefore + errorMsg + errorMsgAfter);
        spannable.setSpan( new CustomTypefaceSpan("sans", latoRegular), 0, errorMsgBefore.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan( new CustomTypefaceSpan("sans", latoBlack), errorMsgBefore.length(), errorMsgBefore.length() + errorMsg.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan( new CustomTypefaceSpan("sans", latoRegular), errorMsgBefore.length() + errorMsg.length(), errorMsgBefore.length() + errorMsg.length() + errorMsgAfter.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSubject.setText(spannable);


        setTypeFace();

        if (model.getIsApproved().equalsIgnoreCase("1"))
        {
            tvApprove.setVisibility(View.VISIBLE);
            tvDenied.setVisibility(View.GONE);
            tvWait.setVisibility(View.GONE);
            tvReSubmit.setVisibility(View.GONE);
        }
        else if (model.getIsApproved().equalsIgnoreCase("0"))
        {
            tvDenied.setVisibility(View.VISIBLE);
            tvApprove.setVisibility(View.GONE);
            tvWait.setVisibility(View.GONE);
            tvReSubmit.setVisibility(View.VISIBLE);
        }
        else if (model.getIsApproved().equalsIgnoreCase("-1"))
        {
            tvWait.setVisibility(View.VISIBLE);
            tvApprove.setVisibility(View.GONE);
            tvDenied.setVisibility(View.GONE);
            tvReSubmit.setVisibility(View.GONE);
        }

        tvReSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(model);
            }
        });

    }

    public void setTypeFace() {
        tvUser.setTypeface(latoRegular);
        tvPosition.setTypeface(latoRegular);
        tvProjectName.setTypeface(latoRegular);
        tvActName.setTypeface(latoRegular);
        tvSubject.setTypeface(latoRegular);
        tvDesc.setTypeface(latoRegular);
        tvFooter.setTypeface(latoBold);
        tvApprove.setTypeface(latoBlack);
        tvDenied.setTypeface(latoBlack);
        tvWait.setTypeface(latoBlack);
        tvReSubmit.setTypeface(latoBlack);
    }
}

package com.sigma.prouds.holder;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.sigma.prouds.CustomTypefaceSpan;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.fragment.MyActivityFragment;
import com.sigma.prouds.model.ApproveTimesheetSendModel;
import com.sigma.prouds.model.ProjectActivityModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.ApproveTimeSheetResponse;

import java.sql.ResultSet;
import java.sql.Timestamp;

import de.greenrobot.event.EventBus;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lucgu.qolfiera on 8/12/2017.
 */

public class ActivityHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private CircleImageView ivProfile;
    private ProudsApplication app;
    private TextView tvUser, tvPosition, tvProjectName, tvActName, tvSubject, tvDesc, tvFooter, tvApprove, tvDenied, tvWait, tvReSubmit;
    private Typeface latoBold, latoBlack, latoRegular;
    private String errorMsg;
    private ApiService service;

    private TextView tvActionDeny, tvActionApprove;

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
        tvActionApprove = (TextView) itemView.findViewById(R.id.tv_approve);
        tvActionDeny = (TextView) itemView.findViewById(R.id.tv_deny);

        app = (ProudsApplication) context.getApplicationContext();

        latoBold = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_bold.ttf");
        latoBlack = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_black.ttf");
        latoRegular = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_regular.ttf");

        tvActionApprove.setTypeface(latoBlack);
        tvActionDeny.setTypeface(latoBlack);
    }

    public void bind(final ProjectActivityModel model)
    {
        tvUser.setText(model.getUserName());
        tvActName.setText(model.getWbsName());
        tvDesc.setText(model.getMessage());

        // set multiple font color
        final String project = "<font color=#3a424c>Project </font>";
        String projectName = model.getProjectName();
        if (Build.VERSION.SDK_INT >= 24) {
            //Log.i("textStyle", "24");
            tvProjectName.setText(Html.fromHtml(project+projectName, 0)); // for 24 api and more
        } else {
            //Log.i("textStyle", "below 24");
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
            tvActionApprove.setVisibility(View.GONE);
            tvActionDeny.setVisibility(View.GONE);
        }
        else if (model.getIsApproved().equalsIgnoreCase("0"))
        {
            tvDenied.setVisibility(View.VISIBLE);
            tvApprove.setVisibility(View.GONE);
            tvWait.setVisibility(View.GONE);
            tvReSubmit.setVisibility(View.VISIBLE);
            tvActionApprove.setVisibility(View.GONE);
            tvActionDeny.setVisibility(View.GONE);
        }
        else if (model.getIsApproved().equalsIgnoreCase("-1"))
        {
            tvWait.setVisibility(View.VISIBLE);
            tvApprove.setVisibility(View.GONE);
            tvDenied.setVisibility(View.GONE);
            tvReSubmit.setVisibility(View.GONE);

            final ProgressDialog dialog = new ProgressDialog(context);
            dialog.setMessage("Please wait");
            dialog.setCancelable(false);

            int profId = Integer.parseInt(app.getSessionManager().getProfId());
            Log.i("Prof id ", profId + "");
            if (profId < 8)
            {
                tvActionApprove.setVisibility(View.VISIBLE);
                tvActionDeny.setVisibility(View.VISIBLE);

                tvActionApprove.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.show();
                        service = ApiUtils.apiService();
                        ApproveTimesheetSendModel sendModel = new ApproveTimesheetSendModel();
                        sendModel.setProjectId(model.getProjectId());
                        sendModel.setConfirm("1");
                        sendModel.setTsId(model.getTsId());
                        service.approveTimesheet(app.getSessionManager().getToken(), sendModel).enqueue(new Callback<ApproveTimeSheetResponse>() {
                            @Override
                            public void onResponse(Call<ApproveTimeSheetResponse> call, Response<ApproveTimeSheetResponse> response)
                            {
                                dialog.dismiss();
                                tvActionApprove.setVisibility(View.GONE);
                                tvActionDeny.setVisibility(View.GONE);
                                tvWait.setVisibility(View.GONE);
                                tvApprove.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onFailure(Call<ApproveTimeSheetResponse> call, Throwable t)
                            {
                                dialog.dismiss();
                                Toast.makeText(context, "Failed to approve", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                tvActionDeny.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v)
                    {
                        dialog.show();
                        service = ApiUtils.apiService();
                        ApproveTimesheetSendModel sendModel = new ApproveTimesheetSendModel();
                        sendModel.setProjectId(model.getProjectId());
                        sendModel.setConfirm("0");
                        sendModel.setTsId(model.getTsId());
                        service.approveTimesheet(app.getSessionManager().getToken(), sendModel).enqueue(new Callback<ApproveTimeSheetResponse>() {
                            @Override
                            public void onResponse(Call<ApproveTimeSheetResponse> call, Response<ApproveTimeSheetResponse> response) {
                                dialog.dismiss();
                                tvActionApprove.setVisibility(View.GONE);
                                tvActionDeny.setVisibility(View.GONE);
                                tvWait.setVisibility(View.GONE);
                                tvDenied.setVisibility(View.VISIBLE);
                                tvReSubmit.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFailure(Call<ApproveTimeSheetResponse> call, Throwable t) {
                                dialog.dismiss();
                                Toast.makeText(context, "Failed to approve", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }

        tvReSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(model);
            }
        });

        //Timestamp timestamp = Timestamp.valueOf(model.getSubmitDate().toString());
        tvFooter.setText(model.getSubmitDate());

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

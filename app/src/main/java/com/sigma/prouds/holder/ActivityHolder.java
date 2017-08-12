package com.sigma.prouds.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.ProjectActivityModel;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lucgu.qolfiera on 8/12/2017.
 */

public class ActivityHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private CircleImageView ivProfile;
    private TextView tvUser, tvPosition, tvProjectName, tvActName, tvSubject, tvDesc, tvApprove, tvDenied, tvWait;

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
        tvApprove = (TextView) itemView.findViewById(R.id.tv_act_approve);
        tvDenied = (TextView) itemView.findViewById(R.id.tv_act_deny);
        tvWait = (TextView) itemView.findViewById(R.id.tv_act_wait_approval);
    }

    public void bind(ProjectActivityModel model)
    {
        tvUser.setText(model.getUserName());
        tvProjectName.setText(model.getProjectName());
        tvActName.setText(model.getWbsName());
        tvSubject.setText(model.getSubject());
        tvDesc.setText(model.getMessage());

        if (model.getIsApproved().equalsIgnoreCase("1"))
        {
            tvApprove.setVisibility(View.VISIBLE);
            tvDenied.setVisibility(View.GONE);
            tvWait.setVisibility(View.GONE);
        }
        else if (model.getIsApproved().equalsIgnoreCase("0"))
        {
            tvDenied.setVisibility(View.VISIBLE);
            tvApprove.setVisibility(View.GONE);
            tvWait.setVisibility(View.GONE);
        }
        else if (model.getIsApproved().equalsIgnoreCase("-1"))
        {
            tvWait.setVisibility(View.VISIBLE);
            tvApprove.setVisibility(View.GONE);
            tvDenied.setVisibility(View.GONE);
        }

    }
}

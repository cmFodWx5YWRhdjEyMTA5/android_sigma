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
    private TextView tvUser, tvPosition, tvProjectName, tvActName;

    public ActivityHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        ivProfile = (CircleImageView) itemView.findViewById(R.id.civ_activity);
        tvUser = (TextView) itemView.findViewById(R.id.tv_act_user);
        tvPosition = (TextView) itemView.findViewById(R.id.tv_act_position);
        tvProjectName = (TextView) itemView.findViewById(R.id.tv_act_project_name);
        tvActName = (TextView) itemView.findViewById(R.id.tv_act_name);
    }

    public void bind(ProjectActivityModel model)
    {
        tvUser.setText(model.getUserName());
        tvProjectName.setText(model.getProjectName());
        tvActName.setText(model.getWbsName());
    }
}

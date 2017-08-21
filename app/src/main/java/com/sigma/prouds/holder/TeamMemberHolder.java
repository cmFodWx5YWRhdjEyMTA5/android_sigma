package com.sigma.prouds.holder;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.TeamModel;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 1414 on 7/24/2017.
 */

public class TeamMemberHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private CircleImageView ivTeamMember;
    private TextView tvNameMember;
    private TextView tvEmailMember;
    private TextView tvLastSeen;
    private Typeface latoRegular, latoBold;
    public TeamMemberHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        ivTeamMember = (CircleImageView) itemView.findViewById(R.id.iv_team_member);
        tvNameMember = (TextView) itemView.findViewById(R.id.tv_name_member);
        tvEmailMember = (TextView) itemView.findViewById(R.id.tv_email_member);
        tvLastSeen = (TextView) itemView.findViewById(R.id.tv_last_seen);

        latoRegular = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_regular.ttf");
        latoBold = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_bold.ttf");
    }

    public void bind(TeamModel model)
    {
        tvNameMember.setText(model.getUserName());
        tvEmailMember.setText(model.getEmail());

        tvNameMember.setTypeface(latoRegular);
        tvEmailMember.setTypeface(latoBold);
        tvLastSeen.setTypeface(latoRegular);
    }
}

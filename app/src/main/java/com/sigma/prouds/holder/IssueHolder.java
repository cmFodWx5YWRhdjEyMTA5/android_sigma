package com.sigma.prouds.holder;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.ProjectIssueModel;

/**
 * Created by 1414 on 7/24/2017.
 */

public class IssueHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private TextView tvSubject;
    private TextView tvReporter;
    private TextView tvIssueStatus;
    private TextView tvIssue;
    private TextView tvDateIssue;
    private TextView tvIssuePriority;
    private Typeface latoBold, latoRegular;
    private ImageView ivPriorityHigh, ivPriorityMed, ivPriorityLow;

    public IssueHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        tvSubject = (TextView) itemView.findViewById(R.id.tv_subject);
        tvReporter = (TextView) itemView.findViewById(R.id.tv_issue_reporter);
        tvIssueStatus = (TextView) itemView.findViewById(R.id.tv_issue_status);
        tvIssue = (TextView) itemView.findViewById(R.id.tv_issue);
        tvDateIssue = (TextView) itemView.findViewById(R.id.tv_date_issue);
        tvIssuePriority = (TextView) itemView.findViewById(R.id.tv_issue_priority);
        ivPriorityHigh = (ImageView) itemView.findViewById(R.id.iv_issue_priority_high);
        ivPriorityMed = (ImageView) itemView.findViewById(R.id.iv_issue_priority_medium);
        ivPriorityLow = (ImageView) itemView.findViewById(R.id.iv_issue_priority_low);

        latoBold = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_bold.ttf");
        latoRegular = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_regular.ttf");
    }

    public void bind(ProjectIssueModel model)
    {
        tvSubject.setText(model.getSubject());
        tvReporter.setText("reported by " + model.getReportedBy());
        tvIssueStatus.setText(model.getStatus());
        tvIssue.setText(model.getNote() + "");
        tvDateIssue.setText(model.getDateIssue());
        tvIssuePriority.setText(model.getPriority());

        if (model.getPriority().equalsIgnoreCase("high")) {
            ivPriorityHigh.setVisibility(View.VISIBLE);
            ivPriorityMed.setVisibility(View.GONE);
            ivPriorityLow.setVisibility(View.GONE);
        }

        else if (model.getPriority().equalsIgnoreCase("medium")) {
            ivPriorityHigh.setVisibility(View.GONE);
            ivPriorityMed.setVisibility(View.VISIBLE);
            ivPriorityLow.setVisibility(View.GONE);
        }

        else if (model.getPriority().equalsIgnoreCase("low")) {
            ivPriorityHigh.setVisibility(View.GONE);
            ivPriorityMed.setVisibility(View.GONE);
            ivPriorityLow.setVisibility(View.VISIBLE);
        }
        setTypeFace();
    }

    public void setTypeFace() {
        tvSubject.setTypeface(latoRegular);
        tvReporter.setTypeface(latoRegular);
        tvIssueStatus.setTypeface(latoRegular);
        tvIssue.setTypeface(latoRegular);
        tvDateIssue.setTypeface(latoBold);
        tvIssuePriority.setTypeface(latoBold);
    }
}

package com.sigma.prouds.holder;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sigma.prouds.PagerActivity;
import com.sigma.prouds.R;
import com.sigma.prouds.model.ProjectModel;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import de.greenrobot.event.EventBus;

/**
 * Created by goy on 7/11/2017.
 */

public class HomeChildHolder extends ChildViewHolder
{
    public TextView project, percen, status;
    public ProgressBar progressBar;
    private LinearLayout llProjectHome;

    public HomeChildHolder(View itemView) {
        super(itemView);

        project = (TextView) itemView.findViewById(R.id.tv_project);
        percen = (TextView) itemView.findViewById(R.id.tv_percen);
        status = (TextView) itemView.findViewById(R.id.tv_status);
        progressBar = (ProgressBar) itemView.findViewById(R.id.pb_home);
        llProjectHome = (LinearLayout) itemView.findViewById(R.id.ll_project_home);

    }

    public void onBind(final ProjectModel model)
    {
        project.setText(model.getProjectName());
        llProjectHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString(PagerActivity.KEY_PROJECT_ID,model.getProjectId() + "");
                bundle.putString(PagerActivity.KEY_PROJECT_NAME, model.getProjectName());
                bundle.putString(PagerActivity.KEY_PROJECT_STATUS, model.getProjectStatus());
                bundle.putString(PagerActivity.KEY_PROJECT_COMPLETED, model.getProjectComplete() + "");
                EventBus.getDefault().post(bundle);
            }
        });
        float progress = Float.parseFloat(model.getProjectComplete());
        int finalProgress = (int) progress;
        progressBar.setProgress(finalProgress);
        percen.setText(model.getProjectComplete() + "%");
        status.setText(model.getProjectStatus());
    }
}

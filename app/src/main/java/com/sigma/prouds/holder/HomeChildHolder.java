package com.sigma.prouds.holder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.sigma.prouds.R;

/**
 * Created by goy on 7/11/2017.
 */

public class HomeChildHolder extends ChildViewHolder {
    public TextView project, percen, status;
    public ProgressBar progressBar;

    public HomeChildHolder(View itemView) {
        super(itemView);

        project = (TextView) itemView.findViewById(R.id.tv_project);
        percen = (TextView) itemView.findViewById(R.id.tv_percen);
        status = (TextView) itemView.findViewById(R.id.tv_status);
        progressBar = (ProgressBar) itemView.findViewById(R.id.pb_home);
    }
}

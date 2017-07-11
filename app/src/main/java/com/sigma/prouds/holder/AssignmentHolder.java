package com.sigma.prouds.holder;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.ProjectAssignmentModel;

/**
 * Created by 1414 on 7/12/2017.
 */

public class AssignmentHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private TextView tvHolderAssignment;

    public AssignmentHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        tvHolderAssignment = (TextView) itemView.findViewById(R.id.tv_holder_assignment);
    }

    public void bind(ProjectAssignmentModel model)
    {
        tvHolderAssignment.setText(model.getProjectName());
        tvHolderAssignment.setTypeface(Typeface.createFromAsset(context.getAssets(), "lato_regular.ttf"));
    }
}

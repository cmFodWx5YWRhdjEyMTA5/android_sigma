package com.sigma.prouds.holder;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.BundleCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.ProjectAssignmentModel;
import com.sigma.prouds.model.ProjectAssignmentNewModel;
import com.sigma.prouds.model.ProjectDetailModel;

import de.greenrobot.event.EventBus;

/**
 * Created by 1414 on 7/12/2017.
 */

public class AssignmentHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private TextView tvHolderAssignment;
    private LinearLayout llAssignment;
    private Typeface latoRegular;

    public AssignmentHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        tvHolderAssignment = (TextView) itemView.findViewById(R.id.tv_holder_assignment);
        llAssignment = (LinearLayout) itemView.findViewById(R.id.ll_assignment);
    }

    public void bind(final ProjectDetailModel model)
    {
        tvHolderAssignment.setText(model.getProjectName());
        tvHolderAssignment.setTypeface(Typeface.createFromAsset(context.getAssets(), "lato_regular.ttf"));

        llAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(model);
            }
        });

        latoRegular = Typeface.createFromAsset(context.getAssets(), "lato_regular.ttf");
        setFontForContainer(llAssignment);
    }

    // set typeface for all text
    private void setFontForContainer(ViewGroup contentLayout) {
        for (int i=0; i < contentLayout.getChildCount(); i++) {
            View view = contentLayout.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView)view).setTypeface(latoRegular);
            }
            else if (view instanceof ViewGroup) {
                setFontForContainer((ViewGroup) view);
            }
        }
    }
}

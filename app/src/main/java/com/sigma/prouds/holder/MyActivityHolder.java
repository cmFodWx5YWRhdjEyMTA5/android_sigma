package com.sigma.prouds.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.ProjectActivityModel;

import org.w3c.dom.Text;

/**
 * Created by 1414 on 8/7/2017.
 */

public class MyActivityHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private TextView tvDesc;

    public MyActivityHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        tvDesc = (TextView) itemView.findViewById(R.id.tv_desc_my_activity);
    }

    public void bind(ProjectActivityModel model)
    {
        tvDesc.setText(model.getMessage());
    }
}

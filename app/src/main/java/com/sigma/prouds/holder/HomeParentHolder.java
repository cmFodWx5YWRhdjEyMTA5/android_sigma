package com.sigma.prouds.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.sigma.prouds.R;

/**
 * Created by goy on 7/11/2017.
 */

public class HomeParentHolder extends ParentViewHolder{
    public TextView unit, updateDate;
    public ImageView dropdown;

    public HomeParentHolder(View itemView) {
        super(itemView);

        unit = (TextView) itemView.findViewById(R.id.tv_unit);
        updateDate = (TextView) itemView.findViewById(R.id.tv_updateDate);
        dropdown = (ImageView) itemView.findViewById(R.id.iv_dropdown);
    }
}

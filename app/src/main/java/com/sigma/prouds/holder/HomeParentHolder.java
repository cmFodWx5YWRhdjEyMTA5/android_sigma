package com.sigma.prouds.holder;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.sigma.prouds.R;
import com.sigma.prouds.model.BusinessUnitExpendableModel;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by goy on 7/11/2017.
 */

public class HomeParentHolder extends GroupViewHolder
{

    public TextView unit, updateDate;
    public ImageView dropdown;
    private Typeface latoBold;

    public HomeParentHolder(Context context, View itemView) {
        super(itemView);
        unit = (TextView) itemView.findViewById(R.id.tv_unit);
        updateDate = (TextView) itemView.findViewById(R.id.tv_updateDate);
        dropdown = (ImageView) itemView.findViewById(R.id.iv_dropdown);
        latoBold = Typeface.createFromAsset(context.getAssets(), "lato_bold.ttf");
    }

    public void bindBUName(ExpandableGroup group)
    {
        unit.setText(group.getTitle());
        unit.setTypeface(latoBold);
    }
}

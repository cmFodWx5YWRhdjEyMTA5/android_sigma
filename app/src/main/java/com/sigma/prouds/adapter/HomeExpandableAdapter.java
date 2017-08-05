package com.sigma.prouds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.sigma.prouds.holder.HomeChildHolder;
import com.sigma.prouds.holder.HomeParentHolder;
import com.sigma.prouds.item.HomeChild;
import com.sigma.prouds.item.HomeParent;
import com.sigma.prouds.R;
import com.sigma.prouds.model.BusinessUnitExpendableModel;
import com.sigma.prouds.model.BusinessUnitModel;
import com.sigma.prouds.model.ProjectModel;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by goy on 7/12/2017.
 */

public class HomeExpandableAdapter extends ExpandableRecyclerViewAdapter<HomeParentHolder, HomeChildHolder>
{

    private Context context;

    public HomeExpandableAdapter(Context context, List<? extends ExpandableGroup> groups)
    {
        super(groups);
        this.context = context;
    }

    @Override
    public HomeParentHolder onCreateGroupViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_home_parent, parent, false);
        return new HomeParentHolder(context, view);
    }

    @Override
    public HomeChildHolder onCreateChildViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_home_child, parent, false);
        return new HomeChildHolder(context, view);
    }

    @Override
    public void onBindChildViewHolder(HomeChildHolder holder, int flatPosition, ExpandableGroup group, int childIndex)
    {
        final ProjectModel model = ((BusinessUnitExpendableModel) group).getItems().get(childIndex);
        holder.onBind(model);

    }

    @Override
    public void onBindGroupViewHolder(HomeParentHolder holder, int flatPosition, ExpandableGroup group)
    {
        holder.bindBUName(group);
    }
}

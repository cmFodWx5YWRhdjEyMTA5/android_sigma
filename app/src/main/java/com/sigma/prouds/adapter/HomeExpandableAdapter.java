package com.sigma.prouds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.sigma.prouds.holder.HomeChildHolder;
import com.sigma.prouds.holder.HomeParentHolder;
import com.sigma.prouds.HomeChild;
import com.sigma.prouds.HomeParent;
import com.sigma.prouds.R;

import java.util.List;

/**
 * Created by goy on 7/12/2017.
 */

public class HomeExpandableAdapter extends ExpandableRecyclerAdapter<HomeParentHolder, HomeChildHolder> {
    private LayoutInflater inflater;

    public HomeExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public HomeParentHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.holder_home_parent, viewGroup, false);
        return new HomeParentHolder(view);
    }

    @Override
    public HomeChildHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.holder_home_child, viewGroup, false);
        return new HomeChildHolder(view);
    }

    @Override
    public void onBindParentViewHolder(HomeParentHolder homeParentHolder, int i, Object o) {
        HomeParent parent = (HomeParent) o;
//        BINDING PARENT HERE
    }

    @Override
    public void onBindChildViewHolder(HomeChildHolder homeChildHolder, int i, Object o) {
        HomeChild child = (HomeChild) o;
//        BINDING CHILD
    }
}

package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.HomeExpandableAdapter;
import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by goy on 7/7/2017.
 */

public class HomeFragment extends Fragment {
    static Context ctx;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(Context context) {
        HomeFragment fragment = new HomeFragment();
        ctx = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rvHome = (RecyclerView) viewRoot.findViewById(R.id.rv_home);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvHome.setLayoutManager(layoutManager);

        HomeExpandableAdapter adapter = new HomeExpandableAdapter(getActivity(), generateList());
        adapter.setCustomParentAnimationViewId(R.id.iv_dropdown);
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        rvHome.setAdapter(adapter);

        return viewRoot;
    }

/* POPULATE LIST HERE */
    private ArrayList<ParentObject> generateList() {
        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        return parentObjects;
    }

}

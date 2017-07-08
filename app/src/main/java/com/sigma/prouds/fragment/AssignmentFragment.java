package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;

/**
 * Created by goy on 7/7/2017.
 */

public class AssignmentFragment extends Fragment {
    static Context ctx;

    public AssignmentFragment() {
        // Required empty public constructor
    }

    public static AssignmentFragment newInstance(Context context) {
        AssignmentFragment fragment = new AssignmentFragment();
        ctx = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assignment, container, false);
    }
}

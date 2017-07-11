package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.adapter.NotifAdapter;

/**
 * Created by goy on 7/7/2017.
 */

public class NotifFragment extends Fragment {
    static Context ctx;

    private RecyclerView rvNotif;
    private NotifAdapter adapter;

    public NotifFragment() {
        // Required empty public constructor
    }

    public static NotifFragment newInstance(Context context) {
        NotifFragment fragment = new NotifFragment();
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

        View viewroot = inflater.inflate(R.layout.fragment_notif, container, false);

        rvNotif = (RecyclerView) viewroot.findViewById(R.id.rv_notif);

        // Setup Adapter


        return viewroot;
    }
}

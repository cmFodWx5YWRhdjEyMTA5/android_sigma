package com.sigma.prouds.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sigma.prouds.R;

import de.greenrobot.event.EventBus;

/**
 * Created by goy on 7/18/2017.
 */

public class DrawerAdapter extends BaseAdapter {
    public static final String KEY_DRAWER_ITEM = "key_drawer_item";
    ImageView ivDrawerClose;
    TextView tvItemTitle;
    Context context;
    LayoutInflater inflater;
    int[] object;

    public DrawerAdapter(Context context, int[] items) {
        this.context = context;
        this.object = items;
    }

    @Override
    public int getCount() {
        return object.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.adapter_drawer, parent, false);

        tvItemTitle = (TextView) itemView.findViewById(R.id.tv_drawer_item);

        final int item = object[position];
        tvItemTitle.setText(item);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(KEY_DRAWER_ITEM, item);
                EventBus.getDefault().post(bundle);
            }
        });

        return itemView;
    }
}

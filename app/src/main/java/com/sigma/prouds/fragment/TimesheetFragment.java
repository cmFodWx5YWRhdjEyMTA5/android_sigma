package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;


/**
 * Created by goy on 7/7/2017.
 */

public class TimesheetFragment extends BaseFragment {
    static Context ctx;
    private CarouselPicker cpDate;

    public static TimesheetFragment newInstance(Context context) {
        TimesheetFragment fragment = new TimesheetFragment();
        ctx = context;
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_timesheet;
    }

    @Override
    protected void workingSpace(View view) {
        assignXML();
    }

    public void assignXML() {
        cpDate = (CarouselPicker) viewRoot.findViewById(R.id.cp_date);
    }

    public void carouselSetup() {
        List<CarouselPicker.PickerItem> dateFilter = new ArrayList<>();
        dateFilter.add(new CarouselPicker.TextItem("one", 14)); // 14 = font size
        dateFilter.add(new CarouselPicker.TextItem("two", 14));
        dateFilter.add(new CarouselPicker.TextItem("three", 14));
        CarouselPicker.CarouselViewAdapter textAdapter = new CarouselPicker.CarouselViewAdapter(getActivity(), dateFilter, 0);
        cpDate.setAdapter(textAdapter);
    }
}

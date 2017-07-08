package com.sigma.prouds.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by goy on 7/7/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //// TODO: 7/7/2017 assign fragment
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public CharSequence getPageTitle(int position) {
        return null;
    }

    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }

}

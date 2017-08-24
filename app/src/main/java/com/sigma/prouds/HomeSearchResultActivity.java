package com.sigma.prouds;

import android.util.Log;

import com.sigma.prouds.base.BaseActivity;

public class HomeSearchResultActivity extends BaseActivity {

    private String searchResult;

    @Override
    protected int getLayout() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void workingSpace() {
        searchResult = getIntent().getExtras().getString("search_result");
        Log.i("search", searchResult);
    }
}

package com.sigma.prouds;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.BusinessUnitExpendableModel;
import com.sigma.prouds.model.ProjectModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomeSearchActivity extends BaseActivity {

    private AutoCompleteTextView actvSearch;
    private ImageView ivSearch;
    private String selected;
    public ArrayList<ProjectModel> list = new ArrayList<ProjectModel>();
    public List<String> listProjectBu = new ArrayList<String>();
    Set<String> hashSet = new HashSet<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void workingSpace() {
        getDataFromHomeFragment();
        getListProjectBu();

        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listProjectBu);
        actvSearch = (AutoCompleteTextView) findViewById(R.id.actv_search);
        actvSearch.setAdapter(adapter);

        actvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = (String)parent.getItemAtPosition(position);
                Log.i("search", selected);
            }
        });


        ivSearch = (ImageView) findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeSearchActivity.this, HomeSearchResultActivity.class);
                intent.putExtra("search_result", selected);
                startActivity(intent);
            }
        });
    }

    public void getDataFromHomeFragment() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras().getBundle(PagerActivity.KEY_TO_HOME_SEARCH);
        list = bundle.getParcelableArrayList(PagerActivity.KEY_SEARCH_LIST);
    }

    public void getListProjectBu() {
        for (int i = 0; i <= list.size()-1; i++) {
            listProjectBu.add(list.get(i).getBuName());
            listProjectBu.add(list.get(i).getProjectName());
        }
        hashSet.addAll(listProjectBu);
        listProjectBu.clear();
        listProjectBu.addAll(hashSet);
    }
}

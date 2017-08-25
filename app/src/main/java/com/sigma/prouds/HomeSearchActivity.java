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
import com.sigma.prouds.model.BusinessUnitModel;
import com.sigma.prouds.model.ProjectModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomeSearchActivity extends BaseActivity {

    private AutoCompleteTextView actvSearch;
    private ImageView ivSearch;
    private String selected;
    public ArrayList<BusinessUnitExpendableModel> arrayList;
    public ArrayList<ProjectModel> list = new ArrayList<ProjectModel>();
    public List<String> listProjectBu = new ArrayList<String>();
    private ArrayList<BusinessUnitModel> listRaw;
    Set<String> hashSet = new HashSet<>();
    private int clickPosition;

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void workingSpace() {
        arrayList = new ArrayList<>();
        listRaw = new ArrayList<>();
        getDataFromHomeFragment();
        getListProjectBu();

        
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listProjectBu);
        actvSearch = (AutoCompleteTextView) findViewById(R.id.actv_search);
        actvSearch.setAdapter(adapter);

        actvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = (String)parent.getItemAtPosition(position);
                clickPosition = listProjectBu.indexOf(selected);
                Log.i("position_1", clickPosition + "");
                Log.i("search", selected);
            }
        });


        ivSearch = (ImageView) findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("search", listRaw);
                bundle.putInt("search_position", clickPosition);
                Intent intent = new Intent(HomeSearchActivity.this, HomeSearchResultActivity.class);
                intent.putExtra("search_result", selected);
                intent.putExtra("search_bundle", bundle);
                startActivity(intent);
            }
        });
    }

    public void getDataFromHomeFragment()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras().getBundle(PagerActivity.KEY_TO_HOME_SEARCH);
        listRaw = (ArrayList<BusinessUnitModel>) bundle.getSerializable(PagerActivity.KEY_SEARCH_LIST);
    }

    public void getListProjectBu()
    {
        for (int i = 0; i <= listRaw.size()-1; i++) {
            listProjectBu.add(listRaw.get(i).getBuName());
            //listProjectBu.add(list.get(i).getProjectName());
            /*for (int j = 0; j <= arrayList.get(i).getProjectList().size() - 1; j++)
            {
                list.add(arrayList.get(i).getProjectList());
            }*/
        }
        hashSet.addAll(listProjectBu);
        listProjectBu.clear();
        listProjectBu.addAll(hashSet);
    }
}

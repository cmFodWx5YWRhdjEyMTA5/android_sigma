package com.sigma.prouds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sigma.prouds.adapter.HomeExpandableAdapter;
import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.BusinessUnitExpendableModel;
import com.sigma.prouds.model.BusinessUnitModel;
import com.sigma.prouds.model.ProjectModel;

import java.util.ArrayList;

public class HomeSearchResultActivity extends BaseActivity {

    private String searchResult;
    private BusinessUnitExpendableModel model;
    private int position;
    public ArrayList<ProjectModel> list = new ArrayList<ProjectModel>();

    private HomeExpandableAdapter adapter;

    private EditText etSearch;
    private RecyclerView rvSearchResult;
    private TextView tvNotFound;
    private ImageView ivBack;
    public ArrayList<BusinessUnitExpendableModel> arrayList;
    public ArrayList<BusinessUnitModel> listRaw;


    @Override
    protected int getLayout() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void workingSpace()
    {
        assignXML();

        arrayList = new ArrayList<>();

        searchResult = getIntent().getExtras().getString("search_result");
        Bundle bundle = getIntent().getExtras().getBundle("search_bundle");
        listRaw = (ArrayList<BusinessUnitModel>) bundle.getSerializable("search");
        position = bundle.getInt("search_position");


        if (position != -1)
        {
            etSearch.setText(searchResult);
            model = new BusinessUnitExpendableModel(listRaw.get(position).getBuName(), listRaw.get(position).getItems());

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            rvSearchResult.setLayoutManager(layoutManager);
            RecyclerView.ItemAnimator animator = rvSearchResult.getItemAnimator();
            if (animator instanceof DefaultItemAnimator) {
                ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
            }
            arrayList.add(model);
            adapter = new HomeExpandableAdapter(this, arrayList);
            rvSearchResult.setAdapter(adapter);
        }
        else if (position == -1)
        {
            Log.i("Search", "not found");
            tvNotFound.setVisibility(View.VISIBLE);
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeSearchResultActivity.this.finish();
            }
        });
        //Log.i("search_item", listRaw.get(position).getBuName());



    }

    public void assignXML()
    {
        etSearch = (EditText) findViewById(R.id.et_search_result);
        rvSearchResult = (RecyclerView) findViewById(R.id.rv_search_result);
        tvNotFound = (TextView) findViewById(R.id.tv_not_found);
        ivBack = (ImageView) findViewById(R.id.iv_back);
    }

    public void onEvent(Bundle bundle)
    {
        if (bundle.containsKey("key_project_id"))
        {
            Intent intent = new Intent(this, ProjectDetailsActivity.class);
            intent.putExtra("key_to_detail-project", bundle);
            startActivity(intent);
        }
    }
}

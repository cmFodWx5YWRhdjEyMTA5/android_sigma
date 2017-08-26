package com.sigma.prouds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.model.BusinessUnitModel;
import com.sigma.prouds.model.ProjectAssignmentModel;
import com.sigma.prouds.model.ProjectDetailModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SearchAssignmentActivity extends BaseActivity {

    public static String KEY_SEARCH_ASSIGNMENT = "key_search_assignment";
    private AutoCompleteTextView actvSearch;
    private ArrayList<ProjectDetailModel> list;
    private ImageView ivSearch;
    private String selected;
    private ArrayList<String> listatc = new ArrayList<>();
    Set<String> hashSet = new HashSet<>();
    private int clickPosition;

    @Override
    protected int getLayout() {
        return R.layout.activity_search_assignment;
    }

    @Override
    protected void workingSpace()
    {
        assignXML();
        list = new ArrayList<>();
        getDataFromAssignmentFragment();
        getListProjectBu();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listatc);
        actvSearch.setAdapter(adapter);

        actvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                selected = (String)parent.getItemAtPosition(position);

            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clickPosition = listatc.indexOf(actvSearch.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putSerializable("search", list);
                bundle.putInt("search_position", clickPosition);

                Intent intent = new Intent(SearchAssignmentActivity.this, SearchAssignmentResultActivity.class);
                intent.putExtra("search_result", selected);
                intent.putExtra("search_bundle", bundle);
                startActivity(intent);
            }
        });

    }

    public void assignXML()
    {
        actvSearch = (AutoCompleteTextView) findViewById(R.id.actv_search_assignment);
        ivSearch = (ImageView) findViewById(R.id.iv_search);
    }

    public void getDataFromAssignmentFragment()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras().getBundle(KEY_SEARCH_ASSIGNMENT);
        list = (ArrayList<ProjectDetailModel>) bundle.getSerializable("data");

    }

    public void getListProjectBu()
    {
        for (int i = 0; i <= list.size()-1; i++) {
            listatc.add(list.get(i).getProjectName());
        }
        hashSet.addAll(listatc);
        listatc.clear();
        listatc.addAll(hashSet);
        Log.i("List", listatc.get(0));
    }
}

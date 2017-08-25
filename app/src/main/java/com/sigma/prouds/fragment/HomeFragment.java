package com.sigma.prouds.fragment;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sigma.prouds.NotifActivity;
import com.sigma.prouds.PagerActivity;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.HomeExpandableAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.model.BusinessUnitExpendableModel;
import com.sigma.prouds.model.ProjectModel;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.ProjectResponse;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/7/2017.
 */

public class HomeFragment extends BaseFragment {
    static Context ctx;
    private ApiService service;
    private ProudsApplication app;
    private RecyclerView rvHome;
    private HomeExpandableAdapter adapter;
    private  List<BusinessUnitExpendableModel> listResult;
    private ImageView ivSearch;
    public ArrayList<BusinessUnitExpendableModel> arrayList;
    public ArrayList<ProjectModel> list = new ArrayList<ProjectModel>();


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(Context context) {
        HomeFragment fragment = new HomeFragment();
        ctx = context;
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void workingSpace(View view)
    {
        app = (ProudsApplication) ctx.getApplicationContext();
        rvHome = (RecyclerView) viewRoot.findViewById(R.id.rv_home);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvHome.setLayoutManager(layoutManager);
        RecyclerView.ItemAnimator animator = rvHome.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        getData();

        ivSearch = (ImageView) view.findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(PagerActivity.KEY_SEARCH_LIST, arrayList);
                EventBus.getDefault().post(bundle);
            }
        });

    }

    public void getData()
    {
        query.id(R.id.pb_home).visible();
        service = ApiUtils.apiService();
        service.getHome(app.getSessionManager().getToken()).enqueue(new Callback<ProjectResponse>()
        {
            @Override
            public void onResponse(Call<ProjectResponse> call, Response<ProjectResponse> response)
            {
                if (query != null)
                {
                    query.id(R.id.pb_home).gone();
                }

                listResult = response.body().getProject();
                arrayList = new ArrayList<>();
//                Log.i("response", response.body().getProject().get(0).getBuName() + "null");
                for (int i = 0; i <= listResult.size() - 1; i++)
                {

                    for (int j = 0; j <= listResult.get(i).getProjectList().size() - 1; j++)
                    {
                        list.add(listResult.get(i).getProjectList().get(j));
                    }
                    arrayList.add(new BusinessUnitExpendableModel(listResult.get(i).getBuName(), list));

                }

                adapter = new HomeExpandableAdapter(ctx, arrayList);
                rvHome.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ProjectResponse> call, Throwable t)
            {
                query.id(R.id.pb_home).gone();
            }
        });

    }

}

package com.sigma.prouds.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
//import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sigma.prouds.ProfileSettingActivity;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.MyActivityAdapter;
import com.sigma.prouds.adapter.PerformanceAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.adapter.PagerAdapter;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.UserdataResponse;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/7/2017.
 */

public class PerformanceFragment extends BaseFragment
{
    public static final int REFRESH_IMAGE_REQ_CODE = 0;

    static Context ctx;
    private ViewPager vpPerformance;
    private PerformanceAdapter adapter;
    private TextView tvUserName, tvRole;
    private ProudsApplication app;
    private TabLayout tabLayout;
    private ImageView ivSetting;
    private ApiService service;
    private CircleImageView ivDp;

    public static PerformanceFragment newInstance(Context context) {
        PerformanceFragment fragment = new PerformanceFragment();
        ctx = context;
        return fragment;
    }


    @Override
    protected int getLayout()
    {
        return R.layout.fragment_performance;
    }

    @Override
    protected void workingSpace(View view)
    {
        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();
        vpPerformance = (ViewPager) view.findViewById(R.id.vp_performance);
        tvUserName = (TextView) view.findViewById(R.id.tv_username);
        tvRole = (TextView) view.findViewById(R.id.tv_role);
        ivDp = (CircleImageView) view.findViewById(R.id.iv_dp);
        tvUserName.setText(app.getSessionManager().getUserName());
        tvRole.setText(app.getSessionManager().getRoleName());
        setViewPager(vpPerformance);

        tabLayout = (TabLayout) view.findViewById(R.id.tl_performance);
        tabLayout.setupWithViewPager(vpPerformance);

        ivSetting = (ImageView) view.findViewById(R.id.iv_setting);
        ivSetting.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ProfileSettingActivity.class);
                intent.putExtra("user", tvUserName.getText().toString());
                startActivityForResult(intent, REFRESH_IMAGE_REQ_CODE);
            }
        });

        query.id(R.id.tv_username).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_black.ttf"));
        query.id(R.id.tv_role).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));

        getProfilePics();

    }

    private void setViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), ctx);
        adapter.addFragment(new ChartFragment().newInstance(ctx), "Performances");
        adapter.addFragment(new MyActivityFragment().newInstance(ctx), "My Activity");
        viewPager.setAdapter(adapter);
    }

    public void getProfilePics()
    {
        service.getUserdata(app.getSessionManager().getToken()).enqueue(new Callback<UserdataResponse>() {
            @Override
            public void onResponse(Call<UserdataResponse> call, Response<UserdataResponse> response)
            {
                try {
                    Glide.with(ctx).load("http://prouds2.telkomsigma.co.id/prouds-api/" + response.body().getUserdata().getImage()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(ivDp);

                }catch (Exception e)
                {

                }

            }

            @Override
            public void onFailure(Call<UserdataResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REFRESH_IMAGE_REQ_CODE)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                getProfilePics();
            }
        }
    }
}

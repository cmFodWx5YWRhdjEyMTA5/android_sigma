package com.sigma.prouds.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.network.ApiService;

/**
 * Created by goy on 7/15/2017.
 */

public class ProjectSettingFragment extends BaseFragment
{

    private ApiService service;
    static Context ctx;
    private String projectId;
    private ProudsApplication app;

    private EditText etprojectId;
    private EditText etProjectName;
    private EditText etBusinessUnit;
    private EditText etRelatedBusinessUnit;
    private EditText etCustomer;
    private EditText etEndCustomer;
    private EditText etProjectValue;
    private EditText etMargin;
    private EditText etDesc;
    private RadioButton rbProject;
    private RadioButton rbNonProject;
    private RadioButton rbYesOperation;
    private RadioButton rbNoOperation;
    private EditText etPm;
    private EditText etAccountManager;
    private EditText etTypeEffort;
    private EditText etProductType;
    private EditText etProjectStatus;
    private EditText etStartDate;
    private EditText etEndDate;
    private Typeface latoRegular;
    private RelativeLayout rlProjectSetting;

    public static ProjectSettingFragment newInstance(Context context, String projectId)
    {
        ProjectSettingFragment fragment = new ProjectSettingFragment();
        ctx = context;
        Bundle args = new Bundle();
        args.putString("project_id", projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_project_setting;
    }

    @Override
    protected void workingSpace(View view)
    {
        etprojectId = (EditText) view.findViewById(R.id.et_projectid);
        etProjectName = (EditText) view.findViewById(R.id.et_project_name);
        etBusinessUnit = (EditText) view.findViewById(R.id.et_business_unit);
        etRelatedBusinessUnit = (EditText) view.findViewById(R.id.et_related_business_unit);
        etCustomer = (EditText) view.findViewById(R.id.et_customer);
        etEndCustomer = (EditText) view.findViewById(R.id.et_end_customer);
        etProjectValue = (EditText) view.findViewById(R.id.et_project_val);
        etMargin = (EditText) view.findViewById(R.id.et_margin);
        etDesc = (EditText) view.findViewById(R.id.et_desc);
        rbProject = (RadioButton) view.findViewById(R.id.rb_project);
        rbNonProject = (RadioButton) view.findViewById(R.id.rb_non_project);
        rbYesOperation = (RadioButton) view.findViewById(R.id.rb_yes);
        rbNoOperation = (RadioButton) view.findViewById(R.id.rb_no);
        etPm = (EditText) view.findViewById(R.id.et_pm);
        etAccountManager = (EditText) view.findViewById(R.id.et_account_manager);
        etTypeEffort = (EditText) view.findViewById(R.id.et_type_effort);
        etProductType = (EditText) view.findViewById(R.id.et_product_type);
        etProjectStatus = (EditText) view.findViewById(R.id.et_project_status);
        etStartDate = (EditText) view.findViewById(R.id.et_start_date);
        etEndDate = (EditText) view.findViewById(R.id.et_end_date);
        rlProjectSetting = (RelativeLayout) view.findViewById(R.id.rl_project_setting);

        latoRegular = Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf");
        setFontForContainer(rlProjectSetting);
    }

    // set typeface for all text
    private void setFontForContainer(ViewGroup contentLayout) {
        for (int i=0; i < contentLayout.getChildCount(); i++) {
            View view = contentLayout.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView)view).setTypeface(latoRegular);
            }
            else if (view instanceof ViewGroup) {
                setFontForContainer((ViewGroup) view);
            }
        }
    }
}

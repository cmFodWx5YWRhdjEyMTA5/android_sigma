package com.sigma.prouds.model;

import android.annotation.SuppressLint;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by 1414 on 7/16/2017.
 */

@SuppressLint("ParcelCreator")
public class BusinessUnitExpendableModel extends ExpandableGroup<ProjectModel>
{
    private String buName;
    private List<ProjectModel> projectList;

    public BusinessUnitExpendableModel(String title, List<ProjectModel> items)
    {
        super(title, items);
    }

    public String getBuName()
    {
        return buName;
    }

    public void setBuName(String buName)
    {
        this.buName = buName;
    }

    public List<ProjectModel> getProjectList()
    {
        return projectList;
    }

    public void setProjectList(List<ProjectModel> projectList)
    {
        this.projectList = projectList;
    }
}

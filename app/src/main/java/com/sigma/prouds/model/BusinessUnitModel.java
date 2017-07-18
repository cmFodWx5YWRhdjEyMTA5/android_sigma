package com.sigma.prouds.model;

import java.util.List;

/**
 * Created by 1414 on 7/16/2017.
 */

public class BusinessUnitModel
{
    private String buName;
    private List<ProjectModel> items;

    public String getBuName()
    {
        return buName;
    }

    public void setBuName(String buName)
    {
        this.buName = buName;
    }

    public List<ProjectModel> getItems()
    {
        return items;
    }

    public void setItems(List<ProjectModel> items)
    {
        this.items = items;
    }
}

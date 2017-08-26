package com.sigma.prouds.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1414 on 7/16/2017.
 */

public class BusinessUnitModel implements Serializable
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

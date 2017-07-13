package com.sigma.prouds.item;

import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;
import java.util.Objects;

/**
 * Created by goy on 7/12/2017.
 */

public class HomeParent implements ParentObject{

//    create instance variable for list childern and other constructor

    private List<Object> projectList;

    @Override
    public List<Object> getChildObjectList() {
        return projectList;
    }


    @Override
    public void setChildObjectList(List<Object> list) {
        projectList = list;
    }
}

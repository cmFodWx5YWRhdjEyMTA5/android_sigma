package com.sigma.prouds.network.response;

import com.sigma.prouds.model.ProjectActivityModel;

import java.util.List;

/**
 * Created by 1414 on 7/10/2017.
 */

public class MyActivityResponse
{
    private List<ProjectActivityModel> activityTimesheet;

    public List<ProjectActivityModel> getActivityTimesheet()
    {
        return activityTimesheet;
    }

    public void setActivityTimesheet(List<ProjectActivityModel> activityTimesheet)
    {
        this.activityTimesheet = activityTimesheet;
    }
}

package com.sigma.prouds.network.response;

import com.sigma.prouds.model.ProjectActivityModel;

import java.util.List;

/**
 * Created by 1414 on 7/10/2017.
 */

public class ProjectActivityResponse
{
    List<ProjectActivityModel> projectActivities;

    public List<ProjectActivityModel> getProjectActivities()
    {
        return projectActivities;
    }

    public void setProjectActivities(List<ProjectActivityModel> projectActivities)
    {
        this.projectActivities = projectActivities;
    }
}

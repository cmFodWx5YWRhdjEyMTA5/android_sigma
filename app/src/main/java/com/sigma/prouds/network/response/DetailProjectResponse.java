package com.sigma.prouds.network.response;

import com.sigma.prouds.model.OverviewModel;
import com.sigma.prouds.model.ProjectPerformanceIndexModel;
import com.sigma.prouds.model.ProjectWorkplanStatusModel;
import com.sigma.prouds.model.TeamModel;
import com.sigma.prouds.model.UserModel;

import java.util.List;

/**
 * Created by 1414 on 7/9/2017.
 */

public class DetailProjectResponse
{
    private UserModel userdata;
    private OverviewModel overview;
    private ProjectPerformanceIndexModel projectPerformanceIndex;
    private List<TeamModel> projectTeam;
    private ProjectWorkplanStatusModel projectWorkplanStatus;

    public UserModel getUserdata()
    {
        return userdata;
    }

    public void setUserdata(UserModel userdata)
    {
        this.userdata = userdata;
    }

    public OverviewModel getOverview()
    {
        return overview;
    }

    public void setOverview(OverviewModel overview)
    {
        this.overview = overview;
    }

    public ProjectPerformanceIndexModel getProjectPerformanceIndex()
    {
        return projectPerformanceIndex;
    }

    public void setProjectPerformanceIndex(ProjectPerformanceIndexModel projectPerformanceIndex)
    {
        this.projectPerformanceIndex = projectPerformanceIndex;
    }

    public List<TeamModel> getProjectTeam()
    {
        return projectTeam;
    }

    public void setProjectTeam(List<TeamModel> projectTeam)
    {
        this.projectTeam = projectTeam;
    }

    public ProjectWorkplanStatusModel getProjectWorkplanStatus()
    {
        return projectWorkplanStatus;
    }

    public void setProjectWorkplanStatus(ProjectWorkplanStatusModel projectWorkplanStatus)
    {
        this.projectWorkplanStatus = projectWorkplanStatus;
    }
}

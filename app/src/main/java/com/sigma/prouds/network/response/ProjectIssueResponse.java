package com.sigma.prouds.network.response;

import com.sigma.prouds.model.ProjectIssueModel;
import com.sigma.prouds.model.UserModel;

import java.util.List;

/**
 * Created by 1414 on 7/9/2017.
 */

public class ProjectIssueResponse
{
    private UserModel userdata;
    private List<ProjectIssueModel> projectIssueList;

    public UserModel getUserdata()
    {
        return userdata;
    }

    public void setUserdata(UserModel userdata)
    {
        this.userdata = userdata;
    }

    public List<ProjectIssueModel> getProjectIssueList()
    {
        return projectIssueList;
    }

    public void setProjectIssueList(List<ProjectIssueModel> projectIssueList)
    {
        this.projectIssueList = projectIssueList;
    }
}

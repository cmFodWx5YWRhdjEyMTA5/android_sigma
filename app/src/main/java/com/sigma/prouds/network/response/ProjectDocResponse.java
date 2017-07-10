package com.sigma.prouds.network.response;

import com.sigma.prouds.model.ProjectDocModel;
import com.sigma.prouds.model.UserModel;

import java.util.List;

/**
 * Created by 1414 on 7/9/2017.
 */

public class ProjectDocResponse
{
    private List<ProjectDocModel> projectDocList;
    private UserModel userdata;

    public List<ProjectDocModel> getProjectDocList()
    {
        return projectDocList;
    }

    public void setProjectDocList(List<ProjectDocModel> projectDocList)
    {
        this.projectDocList = projectDocList;
    }

    public UserModel getUserdata()
    {
        return userdata;
    }

    public void setUserdata(UserModel userdata)
    {
        this.userdata = userdata;
    }
}

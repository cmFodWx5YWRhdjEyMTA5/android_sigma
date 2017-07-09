package com.sigma.prouds.network.response;

import com.sigma.prouds.model.TeamModel;
import com.sigma.prouds.model.UserModel;

/**
 * Created by 1414 on 7/9/2017.
 */

public class TeamMemberResponse
{
    private UserModel userdata;
    private TeamModel projectMember;

    public UserModel getUserdata()
    {
        return userdata;
    }

    public void setUserdata(UserModel userdata)
    {
        this.userdata = userdata;
    }

    public TeamModel getProjectMember()
    {
        return projectMember;
    }

    public void setProjectMember(TeamModel projectMember)
    {
        this.projectMember = projectMember;
    }
}

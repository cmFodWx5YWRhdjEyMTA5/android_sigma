package com.sigma.prouds.network.response;

import com.sigma.prouds.model.UserProjectTimesheetModel;

import java.util.List;

/**
 * Created by lucgu.qolfiera on 8/15/2017.
 */

public class UserProjectTimesheetResponse
{
    List<UserProjectTimesheetModel> userProject;

    public List<UserProjectTimesheetModel> getUserProject() {
        return userProject;
    }

    public void setUserProject(List<UserProjectTimesheetModel> userProject) {
        this.userProject = userProject;
    }
}

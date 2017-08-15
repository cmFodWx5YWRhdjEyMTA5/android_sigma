package com.sigma.prouds.model;

/**
 * Created by lucgu.qolfiera on 8/15/2017.
 */

public class UserProjectTimesheetModel
{
    private String projectName;
    private String projectId;
    private String projectStatus;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
}

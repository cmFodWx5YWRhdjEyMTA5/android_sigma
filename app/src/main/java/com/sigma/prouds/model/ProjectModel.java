package com.sigma.prouds.model;

/**
 * Created by 1414 on 7/18/2017.
 */

public class ProjectModel
{
    private String projectId;
    private String projectName;
    private String buName;
    private String projectComplete;
    private String projectStatus;
    private String projectDesc;
    private String createdBy;

    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getBuName()
    {
        return buName;
    }

    public void setBuName(String buName)
    {
        this.buName = buName;
    }

    public String getProjectComplete()
    {
        return projectComplete;
    }

    public void setProjectComplete(String projectComplete)
    {
        this.projectComplete = projectComplete;
    }

    public String getProjectStatus()
    {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus)
    {
        this.projectStatus = projectStatus;
    }

    public String getProjectDesc()
    {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc)
    {
        this.projectDesc = projectDesc;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }
}

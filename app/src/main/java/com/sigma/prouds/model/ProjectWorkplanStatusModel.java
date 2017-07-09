package com.sigma.prouds.model;

import java.util.List;

/**
 * Created by 1414 on 7/9/2017.
 */

public class ProjectWorkplanStatusModel
{
    private String projectStatus;
    private List<TaskModel> task;

    public String getProjectStatus()
    {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus)
    {
        this.projectStatus = projectStatus;
    }

    public List<TaskModel> getTask()
    {
        return task;
    }

    public void setTask(List<TaskModel> task)
    {
        this.task = task;
    }
}

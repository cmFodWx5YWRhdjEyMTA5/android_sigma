package com.sigma.prouds.network.response;

import com.sigma.prouds.model.ProjectAssignmentModel;

import java.util.List;

/**
 * Created by 1414 on 7/10/2017.
 */

public class MyAssignmentResponse
{
    private List<ProjectAssignmentModel> assignment;

    public List<ProjectAssignmentModel> getAssignment()
    {
        return assignment;
    }

    public void setAssignment(List<ProjectAssignmentModel> assignment)
    {
        this.assignment = assignment;
    }
}

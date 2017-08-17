package com.sigma.prouds.network.response;

import com.sigma.prouds.model.ProjectAssignmentModel;
import com.sigma.prouds.model.ProjectAssignmentNewModel;

import java.util.List;

/**
 * Created by 1414 on 7/10/2017.
 */

public class MyAssignmentNewResponse
{
    private List<ProjectAssignmentNewModel> assignment;

    public List<ProjectAssignmentNewModel> getAssignment() {
        return assignment;
    }

    public void setAssignment(List<ProjectAssignmentNewModel> assignment) {
        this.assignment = assignment;
    }
}

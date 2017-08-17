package com.sigma.prouds.model;

import java.util.List;

/**
 * Created by 1414 on 7/10/2017.
 */

public class ProjectDetailModel
{
    private String projectName;
    private String buName;
    private List<ProjectAssignmentModel> assignmentList;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public List<ProjectAssignmentModel> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<ProjectAssignmentModel> assignmentList) {
        this.assignmentList = assignmentList;
    }
}

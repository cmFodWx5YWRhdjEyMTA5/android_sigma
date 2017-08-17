package com.sigma.prouds.model;

import java.util.List;

/**
 * Created by 1414 on 7/10/2017.
 */

public class ProjectAssignmentNewModel
{
    private String buName;
    private List<ProjectDetailModel> projectDetail;

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public List<ProjectDetailModel> getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(List<ProjectDetailModel> projectDetail) {
        this.projectDetail = projectDetail;
    }
}

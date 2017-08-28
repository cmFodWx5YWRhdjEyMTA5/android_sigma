package com.sigma.prouds.model;

/**
 * Created by lucgu.qolfiera on 8/28/2017.
 */

public class ApproveTimesheetSendModel
{
    private String tsId;
    private String projectId;
    private String confirm;

    public String getTsId() {
        return tsId;
    }

    public void setTsId(String tsId) {
        this.tsId = tsId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}

package com.sigma.prouds.model;

/**
 * Created by lucgu.qolfiera on 9/28/2017.
 */

public class NotificationModel
{
    private String projectId;
    private String userId;
    private String userName;
    private String text;
    private String unixtime;
    private String datetime;
    private String projectName;
    private String projectStatus;
    private String projectComplete;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(String unixtime) {
        this.unixtime = unixtime;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectComplete() {
        return projectComplete;
    }

    public void setProjectComplete(String projectComplete) {
        this.projectComplete = projectComplete;
    }
}

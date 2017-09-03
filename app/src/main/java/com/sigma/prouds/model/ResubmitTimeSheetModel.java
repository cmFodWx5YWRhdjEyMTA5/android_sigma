package com.sigma.prouds.model;

/**
 * Created by lucgu.qolfiera on 8/15/2017.
 */

public class ResubmitTimeSheetModel
{
    private String tsId;
    private String wpId;
    private String tsDate;
    private String hour;
    private String tsSubject;
    private String tsMessage;
    private String latitude;
    private String longtitude;
    private String projectId;
    private String mobile;

    public String getWpId() {
        return wpId;
    }

    public void setWpId(String wpId) {
        this.wpId = wpId;
    }

    public String getTsDate() {
        return tsDate;
    }

    public void setTsDate(String tsDate) {
        this.tsDate = tsDate;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTsSubject() {
        return tsSubject;
    }

    public void setTsSubject(String tsSubject) {
        this.tsSubject = tsSubject;
    }

    public String getTsMessage() {
        return tsMessage;
    }

    public void setTsMessage(String tsMessage) {
        this.tsMessage = tsMessage;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTsId() {
        return tsId;
    }

    public void setTsId(String tsId) {
        this.tsId = tsId;
    }
}

package com.sigma.prouds.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 1414 on 7/10/2017.
 */

public class ProjectDetailModel implements Parcelable
{
    private String projectName;
    private String buName;
    private List<ProjectAssignmentModel> assignmentList;

    public ProjectDetailModel(Parcel in) {
        //projectName = in.readString();
        //buName = in.readString();
    }

    public static final Creator<ProjectDetailModel> CREATOR = new Creator<ProjectDetailModel>() {
        @Override
        public ProjectDetailModel createFromParcel(Parcel in) {
            return new ProjectDetailModel(in);
        }

        @Override
        public ProjectDetailModel[] newArray(int size) {
            return new ProjectDetailModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(projectName);
        dest.writeString(buName);
    }
}

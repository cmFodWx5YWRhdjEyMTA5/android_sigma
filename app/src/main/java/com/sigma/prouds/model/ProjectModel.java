package com.sigma.prouds.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by 1414 on 7/18/2017.
 */

public class ProjectModel implements Parcelable, Serializable
{
    private String projectId;
    private String projectName;
    private String buName;
    private String projectComplete;
    private String projectStatus;
    private String projectDesc;
    private String createdBy;

    protected ProjectModel(Parcel in)
    {
        projectId = in.readString();
        projectName = in.readString();
        buName = in.readString();
        projectComplete = in.readString();
        projectStatus = in.readString();
        projectDesc = in.readString();
        createdBy = in.readString();
    }

    public static final Creator<ProjectModel> CREATOR = new Creator<ProjectModel>()
    {
        @Override
        public ProjectModel createFromParcel(Parcel in)
        {
            return new ProjectModel(in);
        }

        @Override
        public ProjectModel[] newArray(int size)
        {
            return new ProjectModel[size];
        }
    };

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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(projectId);
        dest.writeString(projectName);
        dest.writeString(buName);
        dest.writeString(projectComplete);
        dest.writeString(projectStatus);
        dest.writeString(projectDesc);
        dest.writeString(createdBy);
    }
}

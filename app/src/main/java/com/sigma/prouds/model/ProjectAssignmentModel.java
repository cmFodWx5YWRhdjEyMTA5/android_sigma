package com.sigma.prouds.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1414 on 7/10/2017.
 */

public class ProjectAssignmentModel implements Serializable
{
    private String userId;
    private String userName;
    private String rpId;
    private String projectId;
    private String projectName;
    private String wpId;
    private String wbsId;
    private String wbsName;
    private String startDate;
    private String finishDate;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getRpId()
    {
        return rpId;
    }

    public void setRpId(String rpId)
    {
        this.rpId = rpId;
    }

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

    public String getWpId()
    {
        return wpId;
    }

    public void setWpId(String wpId)
    {
        this.wpId = wpId;
    }

    public String getWbsId()
    {
        return wbsId;
    }

    public void setWbsId(String wbsId)
    {
        this.wbsId = wbsId;
    }

    public String getWbsName()
    {
        return wbsName;
    }

    public void setWbsName(String wbsName)
    {
        this.wbsName = wbsName;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getFinishDate()
    {
        return finishDate;
    }

    public void setFinishDate(String finishDate)
    {
        this.finishDate = finishDate;
    }
}

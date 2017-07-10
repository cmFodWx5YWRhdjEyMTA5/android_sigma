package com.sigma.prouds.model;

/**
 * Created by 1414 on 7/9/2017.
 */

public class ProjectIssueModel
{
    private String issueId;
    private String userId;
    private String userName;
    private String projectId;
    private String projectName;
    private String note;
    private String dateIssue;
    private String evidence;
    private String status;
    private String priority;
    private String duration;
    private String subject;

    public String getIssueId()
    {
        return issueId;
    }

    public void setIssueId(String issueId)
    {
        this.issueId = issueId;
    }

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

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getDateIssue()
    {
        return dateIssue;
    }

    public void setDateIssue(String dateIssue)
    {
        this.dateIssue = dateIssue;
    }

    public String getEvidence()
    {
        return evidence;
    }

    public void setEvidence(String evidence)
    {
        this.evidence = evidence;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getPriority()
    {
        return priority;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    public String getDuration()
    {
        return duration;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }
}

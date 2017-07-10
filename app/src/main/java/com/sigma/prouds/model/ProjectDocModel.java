package com.sigma.prouds.model;

/**
 * Created by 1414 on 7/9/2017.
 */

public class ProjectDocModel
{
    private String docId;
    private String projectId;
    private String docName;
    private String url;
    private String dateUpload;
    private String uploadBy;
    private String docDesc;

    public String getDocId()
    {
        return docId;
    }

    public void setDocId(String docId)
    {
        this.docId = docId;
    }

    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public String getDocName()
    {
        return docName;
    }

    public void setDocName(String docName)
    {
        this.docName = docName;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getDateUpload()
    {
        return dateUpload;
    }

    public void setDateUpload(String dateUpload)
    {
        this.dateUpload = dateUpload;
    }

    public String getUploadBy()
    {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy)
    {
        this.uploadBy = uploadBy;
    }

    public String getDocDesc()
    {
        return docDesc;
    }

    public void setDocDesc(String docDesc)
    {
        this.docDesc = docDesc;
    }
}

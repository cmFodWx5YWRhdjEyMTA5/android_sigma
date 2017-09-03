package com.sigma.prouds.model;

/**
 * Created by lucgu.qolfiera on 9/2/2017.
 */

public class PrivilageModel
{
    private boolean timesheetApproval;
    private boolean editProject;
    private boolean uploadDoc;
    private boolean uploadIssue;

    public boolean isTimesheetApproval() {
        return timesheetApproval;
    }

    public void setTimesheetApproval(boolean timesheetApproval) {
        this.timesheetApproval = timesheetApproval;
    }

    public boolean isEditProject() {
        return editProject;
    }

    public void setEditProject(boolean editProject) {
        this.editProject = editProject;
    }

    public boolean isUploadDoc() {
        return uploadDoc;
    }

    public void setUploadDoc(boolean uploadDoc) {
        this.uploadDoc = uploadDoc;
    }

    public boolean isUploadIssue() {
        return uploadIssue;
    }

    public void setUploadIssue(boolean uploadIssue) {
        this.uploadIssue = uploadIssue;
    }
}

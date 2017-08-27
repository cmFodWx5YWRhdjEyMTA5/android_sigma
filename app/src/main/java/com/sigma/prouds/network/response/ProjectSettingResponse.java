package com.sigma.prouds.network.response;

import com.sigma.prouds.model.AccountManagerModel;
import com.sigma.prouds.model.ProjectManagerModel;
import com.sigma.prouds.model.ProjectSettingModel;
import com.sigma.prouds.model.TypeOfEffortModel;

import java.util.List;

/**
 * Created by lucgu.qolfiera on 8/19/2017.
 */

public class ProjectSettingResponse
{
    private ProjectSettingModel projectSetting;
    private List<TypeOfEffortModel> typeOfEffort;
    private List<ProjectManagerModel> projectManajerList;
    private List<AccountManagerModel> accountManagerList;

    public ProjectSettingModel getProjectSetting() {
        return projectSetting;
    }

    public void setProjectSetting(ProjectSettingModel projectSetting) {
        this.projectSetting = projectSetting;
    }

    public List<TypeOfEffortModel> getTypeOfEffort() {
        return typeOfEffort;
    }

    public void setTypeOfEffort(List<TypeOfEffortModel> typeOfEffort) {
        this.typeOfEffort = typeOfEffort;
    }

    public List<ProjectManagerModel> getProjectManajerList() {
        return projectManajerList;
    }

    public void setProjectManajerList(List<ProjectManagerModel> projectManajerList) {
        this.projectManajerList = projectManajerList;
    }

    public List<AccountManagerModel> getAccountManagerList() {
        return accountManagerList;
    }

    public void setAccountManagerList(List<AccountManagerModel> accountManagerList) {
        this.accountManagerList = accountManagerList;
    }
}

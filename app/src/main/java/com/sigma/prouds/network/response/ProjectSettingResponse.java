package com.sigma.prouds.network.response;

import com.sigma.prouds.model.ProjectSettingModel;

/**
 * Created by lucgu.qolfiera on 8/19/2017.
 */

public class ProjectSettingResponse
{
    private ProjectSettingModel projectSetting;

    public ProjectSettingModel getProjectSetting() {
        return projectSetting;
    }

    public void setProjectSetting(ProjectSettingModel projectSetting) {
        this.projectSetting = projectSetting;
    }
}

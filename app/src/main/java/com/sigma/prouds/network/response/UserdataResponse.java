package com.sigma.prouds.network.response;

import com.sigma.prouds.model.ProfileSettingModel;

/**
 * Created by lucgu.qolfiera on 8/27/2017.
 */

public class UserdataResponse
{
    private ProfileSettingModel userdata;

    public ProfileSettingModel getUserdata() {
        return userdata;
    }

    public void setUserdata(ProfileSettingModel userdata) {
        this.userdata = userdata;
    }
}

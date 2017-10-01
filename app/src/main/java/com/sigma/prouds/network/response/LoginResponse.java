package com.sigma.prouds.network.response;

import com.sigma.prouds.model.NotificationInfoModel;
import com.sigma.prouds.model.PrivilageModel;
import com.sigma.prouds.model.UserModel;

/**
 * Created by 1414 on 7/4/2017.
 */

public class LoginResponse
{
    private UserModel userdata;

    private String token;

    private PrivilageModel privilege;

    private NotificationInfoModel notifInfo;

    public UserModel getUserdata()
    {
        return userdata;
    }

    public void setUserdata(UserModel userdata)
    {
        this.userdata = userdata;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public PrivilageModel getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilageModel privilege) {
        this.privilege = privilege;
    }

    public NotificationInfoModel getNotifInfo() {
        return notifInfo;
    }

    public void setNotifInfo(NotificationInfoModel notifInfo) {
        this.notifInfo = notifInfo;
    }
}

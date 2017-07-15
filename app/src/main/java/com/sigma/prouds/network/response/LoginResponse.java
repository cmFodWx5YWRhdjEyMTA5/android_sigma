package com.sigma.prouds.network.response;

import com.sigma.prouds.model.UserModel;

/**
 * Created by 1414 on 7/4/2017.
 */

public class LoginResponse
{
    private UserModel userdata;

    private String token;

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
}

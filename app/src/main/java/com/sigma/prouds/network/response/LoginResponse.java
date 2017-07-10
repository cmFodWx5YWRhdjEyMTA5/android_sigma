package com.sigma.prouds.network.response;

import com.sigma.prouds.model.UserModel;

/**
 * Created by 1414 on 7/4/2017.
 */

public class LoginResponse
{
    private UserModel userData;

    public UserModel getUserData()
    {
        return userData;
    }

    public void setUserData(UserModel userData)
    {
        this.userData = userData;
    }
}

package com.sigma.prouds.model;

/**
 * Created by 1414 on 7/15/2017.
 */

public class LoginModel
{
    private String userId;
    private String password;
    private String fpid;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFpid()
    {
        return fpid;
    }

    public void setFpid(String fpid)
    {
        this.fpid = fpid;
    }
}

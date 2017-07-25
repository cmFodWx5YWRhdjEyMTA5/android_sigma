package com.sigma.prouds.model;

/**
 * Created by 1414 on 7/9/2017.
 */

public class TeamModel
{
    private String userId;
    private String userName;
    private String email;
    private String profName;
    private String position;

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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getProfName()
    {
        return profName;
    }

    public void setProfName(String profName)
    {
        this.profName = profName;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }
}

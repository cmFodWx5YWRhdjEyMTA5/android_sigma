package com.sigma.prouds.model;

/**
 * Created by 1414 on 7/8/2017.
 */

public class UserModel
{
    private String userId;
    private String userName;
    private String buId;
    private String userTypeId;
    private String supId;
    private String profId;
    private String lastLogin;
    private String loggedIn;
    private String profileName;

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

    public String getBuId()
    {
        return buId;
    }

    public void setBuId(String buId)
    {
        this.buId = buId;
    }

    public String getUserTypeId()
    {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId)
    {
        this.userTypeId = userTypeId;
    }

    public String getSupId()
    {
        return supId;
    }

    public void setSupId(String supId)
    {
        this.supId = supId;
    }

    public String getProfId()
    {
        return profId;
    }

    public void setProfId(String profId)
    {
        this.profId = profId;
    }

    public String getLastLogin()
    {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin)
    {
        this.lastLogin = lastLogin;
    }

    public String getLoggedIn()
    {
        return loggedIn;
    }

    public void setLoggedIn(String loggedIn)
    {
        this.loggedIn = loggedIn;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}

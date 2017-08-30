package com.sigma.prouds.session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 1414 on 7/11/2017.
 */

public class SessionManager
{
    private static final String PREF_NAME = "prouds"; // preference filename
    private static final int MODE_PRIVATE = 0; // preference mode operation
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";

    private static final String LOGIN = "login";
    private static final String TOKEN = "token";
    private static final String PROF_ID = "prof_id";
    private static final String PROFILE_PATH = "profile_path";

    private SharedPreferences pref; // use to access preference
    private SharedPreferences.Editor editor; // use to edit preference

    public SessionManager(Context context)
    {
        pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = pref.edit();
    }

    public void clearSession() // clear all value in preference
    {
        editor.clear();
        editor.commit();
    }

    public boolean isLogin() // check login info
    {
        return pref.getBoolean(LOGIN, false); // default value = false, means no one has login
    }

    public void setLogin(boolean isLogin) // set login info when user already login
    {
        editor.putBoolean(LOGIN, isLogin);
        editor.commit();
    }

    public String getToken()
    {
        return pref.getString(TOKEN, "");
    }

    public void setToken(String token)
    {
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public void setUserName(String userName)
    {
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

    public String getUserName()
    {
        return pref.getString(USER_NAME, "");
    }

    public void setPassword(String password)
    {
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public String getPassword()
    {
        return pref.getString(PASSWORD, "");
    }

    public void setProfId(String profId)
    {
        editor.putString(PROF_ID, profId);
        editor.commit();
    }

    public String getProfId()
    {
        return pref.getString(PROF_ID, "-1");
    }

    public void setProfilePath(String profilePath)
    {
        editor.putString(PROFILE_PATH, profilePath);
        editor.commit();
    }
}

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
    private static final String ROLE_NAME = "role_name";
    private static final String PRIVILAGE_TIMESHEET = "privilage";
    private static final String PRIVILAGE_EDIT_PROJECT = "privilage_edit_project";
    private static final String PRIVILAGE_UPLOAD_ISSUE = "privilage_upload_issue";
    private static final String PRIVILAGE_UPLOAD_DOC = "privilage_upload_doc";

    private static final String UNREAD_NOTIFICATION = "unread_notification";

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

    public void setRoleName(String roleName)
    {
        editor.putString(ROLE_NAME, roleName);
        editor.commit();
    }

    public String getRoleName()
    {
        return pref.getString(ROLE_NAME, "");
    }

    public void setPrivilageTimesheet(boolean privilageTimesheet)
    {
        editor.putBoolean(PRIVILAGE_TIMESHEET, privilageTimesheet);
        editor.commit();
    }

    public boolean getPrivilageTimesheet()
    {
        return pref.getBoolean(PRIVILAGE_TIMESHEET, false);
    }

    public void setPrivilageEditProject(boolean privilageEditProject)
    {
        editor.putBoolean(PRIVILAGE_EDIT_PROJECT, privilageEditProject);
        editor.commit();
    }

    public boolean getPrivilageEditProject()
    {
        return pref.getBoolean(PRIVILAGE_EDIT_PROJECT, false);
    }

    public void setPrivilageUploadIssue(boolean privilageUploadIssue)
    {
        editor.putBoolean(PRIVILAGE_UPLOAD_ISSUE, privilageUploadIssue);
        editor.commit();
    }

    public boolean getPrivilageUploadIssue()
    {
        return pref.getBoolean(PRIVILAGE_UPLOAD_ISSUE, false);
    }

    public void setPrivilageUploadDoc(boolean uploadDoc)
    {
        editor.putBoolean(PRIVILAGE_UPLOAD_DOC, uploadDoc);
        editor.commit();
    }

    public boolean getPrivilageUploadDoc()
    {
        return  pref.getBoolean(PRIVILAGE_UPLOAD_DOC, false);
    }

    public void setUnreadNotification(int count)
    {
        editor.putInt(UNREAD_NOTIFICATION, count);
        editor.commit();
    }

    public int getUnreadNotification()
    {
        return pref.getInt(UNREAD_NOTIFICATION, 0);
    }






}

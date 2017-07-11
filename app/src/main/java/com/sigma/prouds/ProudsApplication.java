package com.sigma.prouds;

import android.app.Application;

import com.sigma.prouds.session.SessionManager;

/**
 * Created by 1414 on 7/11/2017.
 */

public class ProudsApplication extends Application
{
    private SessionManager sessionManager;

    public SessionManager getSessionManager() // check existing SessionManager
    {
        if (sessionManager == null) // no SessionManager is exist
        {
            sessionManager = new SessionManager(this); // create SessionManager
        }
        return sessionManager;
    }
}

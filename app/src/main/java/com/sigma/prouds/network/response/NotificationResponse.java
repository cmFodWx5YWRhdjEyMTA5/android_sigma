package com.sigma.prouds.network.response;

import com.sigma.prouds.model.NotificationInfoModel;
import com.sigma.prouds.model.NotificationModel;

import java.util.List;

/**
 * Created by lucgu.qolfiera on 9/28/2017.
 */

public class NotificationResponse
{
    private List<NotificationModel> notifList;
    private NotificationInfoModel notifInfo;


    public List<NotificationModel> getNotifList() {
        return notifList;
    }

    public void setNotifList(List<NotificationModel> notifList) {
        this.notifList = notifList;
    }

    public NotificationInfoModel getNotifInfo() {
        return notifInfo;
    }

    public void setNotifInfo(NotificationInfoModel notifInfo) {
        this.notifInfo = notifInfo;
    }
}

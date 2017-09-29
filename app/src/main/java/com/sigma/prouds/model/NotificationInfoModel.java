package com.sigma.prouds.model;

/**
 * Created by lucgu.qolfiera on 9/28/2017.
 */

public class NotificationInfoModel
{
    private String currentUserId;
    private String totalUnread;
    private String loadMore;

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getTotalUnread() {
        return totalUnread;
    }

    public void setTotalUnread(String totalUnread) {
        this.totalUnread = totalUnread;
    }

    public String getLoadMore() {
        return loadMore;
    }

    public void setLoadMore(String loadMore) {
        this.loadMore = loadMore;
    }
}

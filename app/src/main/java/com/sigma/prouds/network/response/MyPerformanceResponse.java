package com.sigma.prouds.network.response;

/**
 * Created by lucgu.qolfiera on 8/13/2017.
 */

public class MyPerformanceResponse
{
    private String entry;
    private String utilization;
    private String status;
    private String statusUtilization;

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getUtilization() {
        return utilization;
    }

    public void setUtilization(String utilization) {
        this.utilization = utilization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusUtilization() {
        return statusUtilization;
    }

    public void setStatusUtilization(String statusUtilization) {
        this.statusUtilization = statusUtilization;
    }
}

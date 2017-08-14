package com.sigma.prouds.network.response;

import com.sigma.prouds.model.ChartModel;

import java.util.List;

/**
 * Created by lucgu.qolfiera on 8/13/2017.
 */

public class MyPerformanceYearlyResponse
{
    private List<ChartModel> allentry;
    private List<ChartModel> allhour;

    public List<ChartModel> getAllenrty() {
        return allentry;
    }

    public void setAllenrty(List<ChartModel> allentry) {
        this.allentry = allentry;
    }

    public List<ChartModel> getAllhour() {
        return allhour;
    }

    public void setAllhour(List<ChartModel> allhour) {
        this.allhour = allhour;
    }
}

package com.sigma.prouds.network.response;

import com.sigma.prouds.model.WorkplanNewModel;

import java.util.List;

/**
 * Created by lucgu.qolfiera on 8/29/2017.
 */

public class WorkplanResponse
{
    List<WorkplanNewModel> workplan;

    public List<WorkplanNewModel> getWorkplan() {
        return workplan;
    }

    public void setWorkplan(List<WorkplanNewModel> workplan) {
        this.workplan = workplan;
    }
}

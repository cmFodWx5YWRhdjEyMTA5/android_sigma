package com.sigma.prouds.network.response;

import com.sigma.prouds.model.TaskAddTimeSheetModel;

import java.util.List;

/**
 * Created by lucgu.qolfiera on 8/15/2017.
 */

public class TaskAddTimeSheetResponse
{
    private List<TaskAddTimeSheetModel> task;

    public List<TaskAddTimeSheetModel> getTask() {
        return task;
    }

    public void setTask(List<TaskAddTimeSheetModel> task) {
        this.task = task;
    }
}

package com.sigma.prouds.item;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by goy on 7/12/2017.
 */

public class HomeChild {

    private TextView projectName, percen, status;
    private ProgressBar progressBar;

    public HomeChild(TextView projectName, ProgressBar progressBar, TextView percen, TextView status) {
        this.projectName = projectName;
        this.progressBar = progressBar;
        this.percen = percen;
        this. status = status;
    }
}

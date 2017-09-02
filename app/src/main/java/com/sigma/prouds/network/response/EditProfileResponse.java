package com.sigma.prouds.network.response;

/**
 * Created by lucgu.qolfiera on 8/15/2017.
 */

public class EditProfileResponse
{
    private String imageError;
    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }
}

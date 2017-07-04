package com.sigma.prouds.network;

/**
 * Created by 1414 on 7/4/2017.
 */

public class ApiUtils
{
    public static final String BASE_URL = "http://45.77.45.126/";

    public static ApiService apiService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}

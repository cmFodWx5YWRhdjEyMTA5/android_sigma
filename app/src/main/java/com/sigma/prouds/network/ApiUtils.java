package com.sigma.prouds.network;

/**
 * Created by 1414 on 7/4/2017.
 */

public class ApiUtils
{
    public static final String BASE_URL = "http://prouds2.telkomsigma.co.id/prouds-api/";

    public static ApiService apiService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}

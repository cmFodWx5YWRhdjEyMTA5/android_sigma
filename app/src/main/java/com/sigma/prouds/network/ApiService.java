package com.sigma.prouds.network;

import com.sigma.prouds.model.EmptyModel;
import com.sigma.prouds.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 1414 on 7/4/2017.
 */

public interface ApiService
{
    @POST("dev/login/login")
    Call<LoginResponse> login(@Body EmptyModel model);
}

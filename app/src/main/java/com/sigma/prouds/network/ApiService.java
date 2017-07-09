package com.sigma.prouds.network;

import com.sigma.prouds.model.EmptyModel;
import com.sigma.prouds.network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 1414 on 7/4/2017.
 */

public interface ApiService
{
    @FormUrlEncoded
    @POST("dev/login/login")
    Call<LoginResponse> login(@Field("user_id") String userId, @Field("password") String password, @Field("fpid") String fpid);
}

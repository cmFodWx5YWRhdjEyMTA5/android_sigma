package com.sigma.prouds.network;

import com.sigma.prouds.model.EmptyModel;
import com.sigma.prouds.model.LoginModel;
import com.sigma.prouds.network.response.DetailProjectResponse;
import com.sigma.prouds.network.response.LoginResponse;
import com.sigma.prouds.network.response.MyActivityResponse;
import com.sigma.prouds.network.response.MyAssignmentResponse;
import com.sigma.prouds.network.response.ProjectActivityResponse;
import com.sigma.prouds.network.response.ProjectDocResponse;
import com.sigma.prouds.network.response.ProjectIssueResponse;
import com.sigma.prouds.network.response.ProjectResponse;
import com.sigma.prouds.network.response.TeamMemberResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by 1414 on 7/4/2017.
 */

public interface ApiService
{
    @POST("dev/login/login/")
    Call<LoginResponse> login(@Body LoginModel model);


    @GET("dev/home/detailproject/{project_id}")
    Call<DetailProjectResponse> getDetailProject(@Path("project_id") String projectId, @Header("token") String token);

    @GET("dev/home/")
    Call<ProjectResponse> getHome(@Header("token") String token);

    @GET("dev/home/p_teammember/{project_id}")
    Call<TeamMemberResponse> getTeamMember (@Path("project_id") String projectId, @Header("token") String token);

    @GET("dev/home/projectdoc/{project_id}")
    Call<ProjectDocResponse> getProjectDoc (@Path("project_id") String projectId, @Header("token") String token);

    @GET("dev/home/projectissue/{project_id}")
    Call<ProjectIssueResponse> getProjectIssues (@Path("project_id") String projectId, @Header("token") String token);

    @GET("dev/home/projectactivities/{project_id}")
    Call<ProjectActivityResponse> getProjectActivity (@Path("project_id") String projectId);

    @GET("dev/home/myactivities")
    Call<MyActivityResponse> getMyActivityResponse();

    @GET("dev/home/myassignment")
    Call<MyAssignmentResponse> getMyAssignmentResponse(@Header("token") String token);

}

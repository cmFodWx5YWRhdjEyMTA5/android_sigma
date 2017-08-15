package com.sigma.prouds.network;

import com.sigma.prouds.model.EmptyModel;
import com.sigma.prouds.model.LoginModel;
import com.sigma.prouds.model.PerformanceSendModel;
import com.sigma.prouds.model.PerformanceYearSendModel;
import com.sigma.prouds.model.ProjectListTimesheetSenderModel;
import com.sigma.prouds.model.ReportIssueModel;
import com.sigma.prouds.network.response.AddReportResponse;
import com.sigma.prouds.network.response.DetailProjectResponse;
import com.sigma.prouds.network.response.LoginResponse;
import com.sigma.prouds.network.response.MyActivityResponse;
import com.sigma.prouds.network.response.MyAssignmentResponse;
import com.sigma.prouds.network.response.MyPerformanceResponse;
import com.sigma.prouds.network.response.MyPerformanceYearlyResponse;
import com.sigma.prouds.network.response.ProjectActivityResponse;
import com.sigma.prouds.network.response.ProjectDocResponse;
import com.sigma.prouds.network.response.ProjectIssueResponse;
import com.sigma.prouds.network.response.ProjectResponse;
import com.sigma.prouds.network.response.TeamMemberResponse;
import com.sigma.prouds.network.response.UserProjectTimesheetResponse;

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
    Call<ProjectActivityResponse> getProjectActivity (@Path("project_id") String projectId, @Header("token") String token);

    @GET("dev/home/myactivities")
    Call<MyActivityResponse> getMyActivity(@Header("token") String token);

    @GET("dev/home/myassignment")
    Call<MyAssignmentResponse> getMyAssignmentResponse(@Header("token") String token);

    @POST("dev/report/myperformances")
    Call<MyPerformanceResponse> getMyPerfomance(@Header("token") String token, @Body PerformanceSendModel model);

    @POST("dev/report/myperformances_yearly")
    Call<MyPerformanceYearlyResponse> getYearPerformance(@Header("token") String token, @Body PerformanceYearSendModel model);

    @POST("dev/home/addissue")
    Call<AddReportResponse> sendIssue(@Header("token") String token, @Body ReportIssueModel model);

    @POST("dev/timesheet/view/")
    Call<UserProjectTimesheetResponse> getUserProjectTimesheet(@Header("token") String token, @Body ProjectListTimesheetSenderModel model);

}

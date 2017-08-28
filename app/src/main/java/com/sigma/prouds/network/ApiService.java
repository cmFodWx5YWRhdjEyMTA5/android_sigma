package com.sigma.prouds.network;

import com.sigma.prouds.model.AddTimeSheetModel;
import com.sigma.prouds.model.EditProfileSendModel;
import com.sigma.prouds.model.EditProjectSendModel;
import com.sigma.prouds.model.EmptyModel;
import com.sigma.prouds.model.LoginModel;
import com.sigma.prouds.model.PerformanceSendModel;
import com.sigma.prouds.model.PerformanceYearSendModel;
import com.sigma.prouds.model.ProjectListTimesheetSenderModel;
import com.sigma.prouds.model.ReportIssueModel;
import com.sigma.prouds.model.TaskListTimesheetSenderModel;
import com.sigma.prouds.network.response.AddReportResponse;
import com.sigma.prouds.network.response.AddTimeSheetResponse;
import com.sigma.prouds.network.response.DetailProjectResponse;
import com.sigma.prouds.network.response.EditProfileResponse;
import com.sigma.prouds.network.response.EditProjectResponse;
import com.sigma.prouds.network.response.LoginResponse;
import com.sigma.prouds.network.response.MyActivityResponse;
import com.sigma.prouds.network.response.MyAssignmentNewResponse;
import com.sigma.prouds.network.response.MyAssignmentResponse;
import com.sigma.prouds.network.response.MyPerformanceResponse;
import com.sigma.prouds.network.response.MyPerformanceYearlyResponse;
import com.sigma.prouds.network.response.ProjectActivityResponse;
import com.sigma.prouds.network.response.ProjectDocResponse;
import com.sigma.prouds.network.response.ProjectIssueResponse;
import com.sigma.prouds.network.response.ProjectResponse;
import com.sigma.prouds.network.response.ProjectSettingResponse;
import com.sigma.prouds.network.response.TaskAddTimeSheetResponse;
import com.sigma.prouds.network.response.TeamMemberResponse;
import com.sigma.prouds.network.response.UploadProjectDocResponse;
import com.sigma.prouds.network.response.UploadProjectIssueResponse;
import com.sigma.prouds.network.response.UserProjectTimesheetResponse;
import com.sigma.prouds.network.response.UserdataResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    @POST("login/login")
    Call<LoginResponse> login(@Body LoginModel model);


    @GET("home/detailproject/{project_id}")
    Call<DetailProjectResponse> getDetailProject(@Path("project_id") String projectId, @Header("token") String token);

    @GET("home/")
    Call<ProjectResponse> getHome(@Header("token") String token);

    @GET("home/p_teammember/{project_id}")
    Call<TeamMemberResponse> getTeamMember (@Path("project_id") String projectId, @Header("token") String token);

    @GET("home/projectdoc/{project_id}")
    Call<ProjectDocResponse> getProjectDoc (@Path("project_id") String projectId, @Header("token") String token);

    @GET("home/projectissue/{project_id}")
    Call<ProjectIssueResponse> getProjectIssues (@Path("project_id") String projectId, @Header("token") String token);

    @GET("home/projectactivities/{project_id}")
    Call<ProjectActivityResponse> getProjectActivity (@Path("project_id") String projectId, @Header("token") String token);

    @GET("home/myactivities")
    Call<MyActivityResponse> getMyActivity(@Header("token") String token);

    @GET("home/myassignment")
    Call<MyAssignmentNewResponse> getMyAssignmentResponse(@Header("token") String token);

    @POST("report/myperformances")
    Call<MyPerformanceResponse> getMyPerfomance(@Header("token") String token, @Body PerformanceSendModel model);

    @POST("report/myperformances_yearly")
    Call<MyPerformanceYearlyResponse> getYearPerformance(@Header("token") String token, @Body PerformanceYearSendModel model);


    @POST("timesheet/view/")
    Call<UserProjectTimesheetResponse> getUserProjectTimesheet(@Header("token") String token, @Body ProjectListTimesheetSenderModel model);

    @POST("timesheet/taskList/")
    Call<TaskAddTimeSheetResponse> getTaskTimeSheet(@Header("token") String token, @Body TaskListTimesheetSenderModel model);

    @POST("timesheet/addTimesheet/")
    Call<AddTimeSheetResponse> addTimeSheet(@Header("token") String token, @Body AddTimeSheetModel model);

    @Multipart
    @POST("home/addissue")
    Call<UploadProjectIssueResponse> uploadIssue(@Header("token") String token, @Part("PROJECT_ID") RequestBody id, @Part("SUBJECT") RequestBody subject, @Part("MESSAGE") RequestBody message,
                                                 @Part("PRIORITY") RequestBody priority, @Part MultipartBody.Part image);

    @Multipart
    @POST("home/documentupload/{project_id}")
    Call<UploadProjectDocResponse> uploadDoc(@Header("token") String token, @Path("project_id") String projectId, @Part("desc") RequestBody desc, @Part MultipartBody.Part doc);

    @GET("project/editProject_view/{project_id}")
    Call<ProjectSettingResponse> getProjectSetting(@Header("token") String token, @Path("project_id") String projectId);

    @GET("home/userdata")
    Call<UserdataResponse> getUserdata(@Header("token") String token);

    @POST("home/edit_user/")
    Call<EditProfileResponse> editProfile(@Header("token") String token, @Body EditProfileSendModel model);

    @POST("project/editProject_action/")
    Call<EditProjectResponse> editProject(@Header("token") String token, @Body EditProjectSendModel model);
}

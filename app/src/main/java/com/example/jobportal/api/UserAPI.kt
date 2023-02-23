package com.example.jobportal.api

import com.example.jobportal.models.Request.LoginRequest
import com.example.jobportal.models.Response.LoginResponse
import com.example.jobportal.models.Request.SignUpRequest
import com.example.jobportal.models.Response.JobListResponse
import com.example.jobportal.models.Response.NetworkResponse
import com.example.jobportal.models.Response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserAPI {
    @POST("/api/v1//auth/login/")
    suspend fun signIn(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    @POST("/api/v1//auth/register/")
    suspend fun signUp(@Body signupRequest: SignUpRequest) : Response<SignUpResponse>

    @GET("/api/v1/candidates/jobs")
    suspend fun getAvailableJobs(@Query("page") page:Int=1): Response<NetworkResponse>
}
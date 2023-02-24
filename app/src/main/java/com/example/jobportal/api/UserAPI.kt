package com.example.jobportal.api

import com.example.jobportal.models.Request.LoginRequest
import com.example.jobportal.models.Response.LoginResponse
import com.example.jobportal.models.Request.SignUpRequest
import com.example.jobportal.models.Response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("/api/v1//auth/login/")
    suspend fun signIn(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    @POST("/api/v1//auth/register/")
    suspend fun signUp(@Body signupRequest: SignUpRequest) : Response<SignUpResponse>
}
package com.example.jobportal.api

import com.example.jobportal.models.Response.apply_jobs.ApplyJob
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsAPI {

    @GET("/api/v1/candidates/jobs")
    suspend fun getAvailableJobs(@Query("page") page:Int=1): Response<ApplyJob>

//    @GET("/api/v1/candidates/jobs/applied")
//    suspend fun getAppliedJobs(@Query("page") page:Int=1): Response<JobApplied>


}
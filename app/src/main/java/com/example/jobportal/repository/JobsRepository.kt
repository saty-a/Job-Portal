package com.example.jobportal.repository

import android.util.Log
import com.example.jobportal.api.UserAPI
import com.example.jobportal.utils.Constants.TAG
import javax.inject.Inject

class JobsRepository @Inject constructor(private val jobApi: UserAPI) {
    suspend fun resgisterUser(){

      //  val response = UserAPI.getAvailableJobs()

       // Log.d(TAG,response.body().toString())
    }
}
package com.example.jobportal.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobportal.api.JobsAPI
import com.example.jobportal.models.Response.apply_jobs.ApplyJob
import com.example.jobportal.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobsRepository @Inject constructor(private val jobApi: JobsAPI) {


    // Live data for change purpose
    private val _userResponseLiveData = MutableLiveData<NetworkResult<ApplyJob>>()

    // publically accessable this live data used for only read only purpose
    val userResponseLiveData: LiveData<NetworkResult<ApplyJob>>
        get() = _userResponseLiveData

    suspend fun getAllJobs(){
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = jobApi.getAvailableJobs()
        if (response.isSuccessful && response.body() != null) {
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }

//    private fun handleResponse(response: Response<ApplyJob>, message: String) {
//        if (response.isSuccessful && response.body() != null) {
//            _userResponseLiveData.postValue(NetworkResult.Success(Pair(true, message)))
//        } else {
//            _userResponseLiveData.postValue(NetworkResult.Success(Pair(false, "Something went wrong")))
//        }
//    }

}
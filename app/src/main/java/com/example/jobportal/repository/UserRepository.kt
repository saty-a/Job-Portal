package com.example.jobportal.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobportal.api.UserAPI
import com.example.jobportal.models.Request.LoginRequest
import com.example.jobportal.models.Request.SignUpRequest
import com.example.jobportal.models.Response.LoginResponse
import com.example.jobportal.models.Response.SignUpResponse
import com.example.jobportal.utils.Constants.TAG
import com.example.jobportal.utils.NetworkResult
import com.example.jobportal.utils.SessionManager
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userAPI: UserAPI) {

    // Live data for change purpose
    private val _userResponseLiveData = MutableLiveData<NetworkResult<LoginResponse>>()
    // this live data used for only read only purpose
    val userResponseLiveData: LiveData<NetworkResult<LoginResponse>>
    get() = _userResponseLiveData

    private val _userResponseLiveDataSignUp = MutableLiveData<NetworkResult<SignUpResponse>>()
    // this live data used for only read only purpose
    val userResponseLiveDataSignUp: LiveData<NetworkResult<SignUpResponse>>
        get() = _userResponseLiveDataSignUp

    suspend fun loginUser(loginRequest: LoginRequest){
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userAPI.signIn(loginRequest)
        handleResponseLogin(response)
    }

    suspend fun resgisterUser(signupRequest: SignUpRequest){
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userAPI.signUp(signupRequest)
        Log.d(TAG, response.toString()+"response ")
        handleResponseSignup(response)
        }

    private fun handleResponseLogin(response: Response<LoginResponse>) {

        if (response.isSuccessful && response.body() != null) {
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _userResponseLiveData.postValue(NetworkResult.Error("Something went Wrong"))
        }
    }

    private fun handleResponseSignup(response: Response<SignUpResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userResponseLiveDataSignUp.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveDataSignUp.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _userResponseLiveDataSignUp.postValue(NetworkResult.Error("Something went Wrong"))

        }
    }
}
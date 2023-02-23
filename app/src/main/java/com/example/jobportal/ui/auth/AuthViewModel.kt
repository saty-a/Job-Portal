package com.example.jobportal.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobportal.models.Request.LoginRequest
import com.example.jobportal.models.Request.SignUpRequest
import com.example.jobportal.models.Response.LoginResponse
import com.example.jobportal.models.Response.SignUpResponse
import com.example.jobportal.repository.UserRepository
import com.example.jobportal.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val userResponseLiveData:LiveData<NetworkResult<LoginResponse>>
    get() = userRepository.userResponseLiveData

    val userResponseLiveDataSignUp:LiveData<NetworkResult<SignUpResponse>>
        get() = userRepository.userResponseLiveDataSignUp

    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            userRepository.loginUser(loginRequest)
        }
    }

    fun registerUser(signupRequest: SignUpRequest) {
        viewModelScope.launch {
            userRepository.resgisterUser(signupRequest)
        }
    }

}
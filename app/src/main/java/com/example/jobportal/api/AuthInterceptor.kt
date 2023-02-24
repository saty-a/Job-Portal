package com.example.jobportal.api

import com.example.jobportal.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val sessionManager: SessionManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = sessionManager.getToken()
        if (token != null) {
            request.addHeader("Authorization", token)
        }
        return chain.proceed(request.build())
    }
}
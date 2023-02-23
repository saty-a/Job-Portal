package com.example.jobportal.models.Response

import com.example.jobportal.models.Data

data class LoginResponse(
    val code: Int,
    val `data`: Data,
    val success: Boolean
)
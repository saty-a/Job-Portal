package com.example.jobportal.models.Response

import com.example.jobportal.models.Data

data class SignUpResponse(
    val code: Int,
    val `data`: Data,
    val success: Boolean
)
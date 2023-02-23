package com.example.jobportal.models.Request

data class SignUpRequest(
    val name: String,
    val email: String,
    val userRole: Int,
    val password: String,
    val confirmPassword: String,
    val skills: String,
)
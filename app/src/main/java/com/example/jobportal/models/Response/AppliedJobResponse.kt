package com.example.jobportal.models.Response

data class AppliedJobResponse(
    val code: Int,
    val `data`: List<AppliedJob>,
    val success: Boolean
)
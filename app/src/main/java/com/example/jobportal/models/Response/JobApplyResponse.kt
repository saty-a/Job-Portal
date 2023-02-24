package com.example.jobportal.models.Response

data class JobApplyResponse(
    val code: Int,
    val `data`: List<JobApply>,
    val metadata: MetadataX,
    val success: Boolean
)
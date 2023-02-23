package com.example.jobportal.models.Response

data class NetworkResponse(
    val code: Int,
    val `data`: List<ResponseItem>,
    val metadata: Metadata,
    val success: Boolean
)
package com.example.jobportal.models.Response

data class AppliedJob(
    val createdAt: String,
    val description: String,
    val id: String,
    val location: String,
    val title: String,
    val updatedAt: String
)
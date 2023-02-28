package com.example.jobportal.models.Response.apply_jobs

data class ApplyJob(
    val code: Int,
    val `data`: ArrayList<Data>,
    val metadata: Metadata,
    val success: Boolean
)
package com.example.jobportal.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobportal.repository.JobsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(private val jobsRepository: JobsRepository):ViewModel() {

    val userResponseliveData get() =jobsRepository.userResponseLiveData

    fun getAllJobs(){
        viewModelScope.launch {
            jobsRepository.getAllJobs()
        }
    }

}
package com.example.jobportal.ui.feed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobportal.adapter.ApplyJobAdapter
import com.example.jobportal.api.JobsAPI
import com.example.jobportal.databinding.FragmentJobsForYouBinding
import com.example.jobportal.models.Response.apply_jobs.ApplyJob
import com.example.jobportal.models.Response.apply_jobs.Data
import com.example.jobportal.utils.Constants.TAG
import com.example.jobportal.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class JobsForYouFragment : Fragment() {

    private var _binding : FragmentJobsForYouBinding ? =null
    private val binding get() = _binding!!
    private val jobViewModel by viewModels<JobViewModel>()

    @Inject
    lateinit var jobsAPI: JobsAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJobsForYouBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // this creates a vertical layout Manager
       // binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val jobList = ArrayList<Data>()
        CoroutineScope(Dispatchers.IO).launch {
            val response = jobsAPI.getAvailableJobs().body()?.data
          //  jobList.addAll(response!!.toList())
                // This will pass the ArrayList to our Adapter
                // Setting the Adapter with the recyclerview
        }

        val adapter = ApplyJobAdapter(jobList)
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(context)
//        binding.recyclerView.setHasFixedSize(true)
        bindObservers()
        Log.d(TAG, ("List of data items : " + jobList.toArray()))
    }

    private fun bindObservers() {
        jobViewModel.userResponseliveData.observe(viewLifecycleOwner, Observer {

            when (it) {
                is NetworkResult.Success -> {
                    val adapter = ApplyJobAdapter(it.data!!.data)
                    Log.d(TAG, it.data.data.toString())
                    binding.recyclerView.adapter = adapter
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Loading -> {
                   // binding.progressBar.isVisible = true
                }
                else -> {

                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
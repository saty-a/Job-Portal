package com.example.jobportal.ui.feed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobportal.adapter.ApplyJobAdapter
import com.example.jobportal.api.JobsAPI
import com.example.jobportal.databinding.FragmentJobsForYouBinding
import com.example.jobportal.models.Response.JobApply
import com.example.jobportal.models.Response.JobApplyResponse
import com.example.jobportal.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class JobsForYouFragment : Fragment() {

    private var _binding : FragmentJobsForYouBinding ? =null
    private val binding get() = _binding!!

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
        binding.recyclerView.layoutManager = LinearLayoutManager(context)


        // ArrayList of class ItemsViewModel
        val data = ArrayList<JobApply>()
        CoroutineScope(Dispatchers.IO).launch {
            val response = jobsAPI.getAvailableJobs().body()?.data
            data.addAll(response!!.toSet())
            Log.d(TAG, ("List of data items : " + data.size))
        }


        // This will pass the ArrayList to our Adapter
        val adapter = ApplyJobAdapter(data)
        // Setting the Adapter with the recyclerview
        binding.recyclerView.adapter = adapter

//        binding.floatingBtn.setOnClickListener {
//            data.add(jobs("Web Dev", "lsdajfjalsdfj lflfasdjflakjfd jladf", "delhi"))
//            adapter.notifyDataSetChanged()
//            println("fb clicked")
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun loadFragment(fragment: Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container,fragment)
//        transaction.commit()
//    }

}
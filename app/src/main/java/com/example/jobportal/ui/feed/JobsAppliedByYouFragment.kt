package com.example.jobportal.ui.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobportal.adapter.AppliedJobsAdapter
import com.example.jobportal.databinding.FragmentJobsForYouBinding
import com.example.jobportal.models.jobs

class JobsAppliedByYouFragment : Fragment() {

    private var _binding: FragmentJobsForYouBinding ? = null
    private val binding get() = _binding!!

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

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        var data = ArrayList<jobs>()

        data.add(jobs("Dev","lsdjflaskfj asdfjlasjdflj","Gurugram"))
        data.add(jobs("Kum","lsdjflaskfj asdfjlasjdflj","Delhi"))
        data.add(jobs("Sun","lsdjflaskfj asdfjlasjdflj","Dhanbad"))
        data.add(jobs("Green","lsdjflaskfj asdfjlasjdflj","Roorkee"))
        data.add(jobs("Blue","lsdjflaskfj asdfjlasjdflj","Sharanput"))
        data.add(jobs("Red","lsdjflaskfj asdfjlasjdflj","Dehradun"))
        data.add(jobs("White","lsdjflaskfj asdfjlasjdflj","New Delhi"))



        val adapter = AppliedJobsAdapter(data)

        binding.recyclerView.adapter= adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
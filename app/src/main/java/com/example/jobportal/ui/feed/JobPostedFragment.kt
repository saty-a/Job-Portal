package com.example.jobportal.ui.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jobportal.R
import com.example.jobportal.databinding.FragmentJobPostedBinding

class JobPostedFragment : Fragment() {

    private var _binding:FragmentJobPostedBinding ? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJobPostedBinding.inflate(inflater,container,false)
        return binding.root
    }

}
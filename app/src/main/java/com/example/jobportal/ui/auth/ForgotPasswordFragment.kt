package com.example.jobportal.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.jobportal.databinding.FragmentForgotPasswordBinding
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class ForgotPasswordFragment : Fragment() {
    private var _binding:FragmentForgotPasswordBinding? = null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitBtn.setOnClickListener {
            if (
                binding.fpmailEt.validator().validEmail().addErrorCallback {
                    binding.fpmailEt.error = it
                }.check()
            ){
                Toast.makeText(context,"Validation Completed!",Toast.LENGTH_SHORT).show()
            }
        }

        binding.backTv.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}
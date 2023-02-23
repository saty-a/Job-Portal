package com.example.jobportal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.jobportal.databinding.FragmentResetBinding
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class ResetFragment : Fragment() {

    private var _binding :FragmentResetBinding ? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentResetBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resetBtn.setOnClickListener {
            if (binding.passEt.validator().nonEmpty().atleastOneNumber().atleastOneUpperCase().atleastOneSpecialCharacters().addErrorCallback {
                    binding.passEt.error = it
                }
                    .check()
                && binding.confirmPasswordEt.validator().nonEmpty().atleastOneNumber().atleastOneUpperCase().atleastOneSpecialCharacters().addErrorCallback {
                    binding.passEt.error = it
                }
                    .check()
            ){
                Toast.makeText(context,"Validation Completed",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
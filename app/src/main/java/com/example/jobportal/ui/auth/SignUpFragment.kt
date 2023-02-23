package com.example.jobportal.ui.auth

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.jobportal.R
import com.example.jobportal.databinding.FragmentSignUpBinding
import com.example.jobportal.models.Request.SignUpRequest
import com.example.jobportal.ui.feed.FeedActivity
import com.example.jobportal.utils.Constants.TAG
import com.example.jobportal.utils.NetworkResult
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding:FragmentSignUpBinding? = null
    private val binding get() =_binding!!
    private val authViewModel by viewModels<AuthViewModel>()
    private var userid:Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentSignUpBinding.inflate(inflater,container,false)

        binding.recruiterBtn.setOnClickListener {
            setUserId(1)
            binding.recruiterBtn.apply {
                setBackgroundColor(context.getColor(R.color.primary))
                setTextColor(context.getColor(R.color.white))
                iconTint = context?.let { it1 ->
                    ContextCompat.getColor(
                        it1, R.color.white)
                }?.let { it2 -> ColorStateList.valueOf(it2) }

            }

            binding.candidateBtn.apply {
                setBackgroundColor(context.getColor(R.color.white))
                setTextColor(context.getColor(R.color.black))

                iconTint = context?.let { it1 ->
                    ContextCompat.getColor(
                        it1, R.color.grey)
                }?.let { it2 -> ColorStateList.valueOf(it2) }
            }

        }

        binding.candidateBtn.setOnClickListener {
            setUserId(0)
            binding.candidateBtn.apply {
                setBackgroundColor(context.getColor(R.color.primary))
                setTextColor(context.getColor(R.color.white))

                iconTint = context?.let { it1 ->
                    ContextCompat.getColor(
                        it1, R.color.white)
                }?.let { it2 -> ColorStateList.valueOf(it2) }

            }

            binding.recruiterBtn.apply {
                setBackgroundColor(context.getColor(R.color.white))
                setTextColor(context.getColor(R.color.black))

                iconTint = context?.let { it1 ->
                    ContextCompat.getColor(
                        it1, R.color.grey)
                }?.let { it2 -> ColorStateList.valueOf(it2) }

            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupBtn.setOnClickListener {

            if (binding.nameEt.validator().noSpecialCharacters().noNumbers().nonEmpty().addErrorCallback {
                    binding.nameEt.error = it
                }.check()
                &&
                        binding.mailEt.validator().nonEmpty().validEmail().addErrorCallback {
                            binding.mailEt.error = it}.check()
                &&
                        binding.passwordEt.validator().nonEmpty().atleastOneNumber().atleastOneUpperCase().atleastOneSpecialCharacters().addErrorCallback {
                            binding.passwordEt.error = it }.check()
                &&
                        binding.confirmPasswordEt.validator().nonEmpty().atleastOneNumber().atleastOneSpecialCharacters().atleastOneUpperCase().addErrorCallback {
                            binding.confirmPasswordEt.error = it }.check()
                &&
                        binding.skillsEt.validator().nonEmpty().addErrorCallback {
                            binding.skillsEt.error = it
                        }.check()
            ){
                authViewModel.registerUser(
                    SignUpRequest(binding.nameEt.text.toString(),binding.mailEt.text.toString(),
                        userid,binding.passwordEt.text.toString(),binding.confirmPasswordEt.text.toString(),binding.skillsEt.text.toString()
                    )
                )
                bindObserver()
            }
        }

        binding.loginBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.backTv.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun bindObserver() {
        authViewModel.userResponseLiveDataSignUp.observe(viewLifecycleOwner, Observer {
            binding.signupBtn.visibility =View.GONE
            binding.progressBar.visibility = View.VISIBLE
            when (it) {
                is NetworkResult.Success -> {
                    Toast.makeText(context, "Account Created Successful!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
                is NetworkResult.Error -> {
                    Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.progressBar.visibility = View.GONE
                        binding.signupBtn.visibility = View.VISIBLE
                    }, 1000)
                }
                is NetworkResult.Loading -> {
                  // binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    fun setUserId(value:Int){
        userid=value
    }

}
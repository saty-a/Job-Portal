package com.example.jobportal.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.jobportal.R
import com.example.jobportal.databinding.FragmentLoginBinding
import com.example.jobportal.models.Request.LoginRequest
import com.example.jobportal.models.Response.LoginResponse
import com.example.jobportal.ui.feed.FeedActivity
import com.example.jobportal.utils.NetworkResult
import com.example.jobportal.utils.SessionManager
import com.google.gson.JsonObject
import com.wajahatkarim3.easyvalidation.core.view_ktx.contains
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    // Accessing the Binding class of fragment_login layout and creating it's object
    private var _binding: FragmentLoginBinding? = null

    // For not using !! null safety again and again creating reference object for _binding
    private val binding get() = _binding!!

    private val authViewModel by viewModels<AuthViewModel>()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.forgotpassTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding.createAccountTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        // Returning the root view element of the layout
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.loginBtn.visibility = View.VISIBLE
//        binding.progressBar.visibility = View.GONE


        binding.loginBtn.setOnClickListener {
            if (binding.emailEt.validator().nonEmpty()
                    .validEmail()
                    .addErrorCallback {
                        binding.emailEt.error = it
                    }
                    .check()
                &&
                binding.passEt.validator().nonEmpty().atleastOneNumber()
                    .atleastOneSpecialCharacters()
                    .atleastOneUpperCase()
                    .addErrorCallback {
                        binding.passEt.error = it
                    }
                    .check()

            ) {
                    authViewModel.loginUser(
                        LoginRequest(
                            binding.emailEt.text.toString(),
                            binding.passEt.text.toString()
                        )
                    )
                    bindObserver()
            }
        }

    }

    private fun bindObserver() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.loginBtn.visibility =View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE
            when (it) {
                is NetworkResult.Success -> {
                    Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                    sessionManager.saveAuthToken(it.data!!.data.token)
                    Toast.makeText(context,sessionManager.getToken().toString(),Toast.LENGTH_SHORT).show()
                        startActivity(Intent(activity, FeedActivity::class.java))
                        activity?.finish()
                }
                is NetworkResult.Error -> {
                    Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.loginBtn.visibility = View.VISIBLE
                    }, 1000)
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    // Removing the binding object from the memory when the view gets destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
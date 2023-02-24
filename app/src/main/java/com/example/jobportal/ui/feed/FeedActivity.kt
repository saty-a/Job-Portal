package com.example.jobportal.ui.feed

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jobportal.MainActivity
import com.example.jobportal.R
import com.example.jobportal.api.JobsAPI
import com.example.jobportal.databinding.ActivityFeedBinding
import com.example.jobportal.utils.Constants.TAG
import com.example.jobportal.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

                binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home->{
                    Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show()
                    replaceFragment(JobsForYouFragment())
                }

                R.id.appliedJob -> {
                    Toast.makeText(this,"Applied",Toast.LENGTH_SHORT).show()
                    replaceFragment(JobsAppliedByYouFragment())
                }

                R.id.logout ->{
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Alert!")
                    builder.setMessage("Are you sure you want to Logout?")
                    builder.setIcon(android.R.drawable.ic_dialog_alert)
                    builder.setPositiveButton("Yes"){dialogInterface, which ->
                        logout()
                        Toast.makeText(applicationContext,"You have successfully logged out!",Toast.LENGTH_LONG).show()
                    }
                    builder.setNegativeButton("No"){dialogInterface, which ->
                    }
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
            }
            return@setOnItemSelectedListener true
        }

    }


    private fun replaceFragment(fragment : Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment,fragment)
        fragmentTransaction.commit()
    }

    private fun logout(){

        sessionManager.clearloginPrefs()
        startActivity(Intent(this,MainActivity::class.java))
    }

}
package com.example.jobportal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jobportal.ui.feed.FeedActivity
import com.example.jobportal.utils.Constants.TAG
import com.example.jobportal.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            Log.d(TAG,sessionManager.getUserId().toString())

        if (sessionManager.getToken()!=null){
            startActivity(Intent(this,FeedActivity::class.java))
        }


    }
}
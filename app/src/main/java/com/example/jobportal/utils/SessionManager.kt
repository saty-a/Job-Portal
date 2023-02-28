package com.example.jobportal.utils

import android.content.Context
import android.content.SharedPreferences
import android.media.session.MediaSession.Token
import android.util.Log
import com.example.jobportal.R
import com.example.jobportal.repository.UserRepository
import com.example.jobportal.utils.Constants.PREFS_TOKEN_FILE
import com.example.jobportal.utils.Constants.TAG
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionManager@Inject constructor(@ApplicationContext context: Context) {
    private var prefs=context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)
    val editor= prefs.edit()

    /// Function to save the auth token
    fun saveAuthToken(token: String){
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }

    /// Function for fetching the shared Preferences data
    fun getToken():String?{
        return prefs.getString(USER_TOKEN,null)
    }

    fun saveUserId(userId:Int){
        editor.putInt("id",userId)
        editor.apply()
    }

    fun getUserId(): Int {
        return prefs.getInt("id",2)
    }

    // Clearing the token of the user from sharedPrefs
    fun clearloginPrefs(){
        editor.clear()
        editor.commit()
    }

    companion object{
        const val USER_TOKEN="user_token"
    }

}
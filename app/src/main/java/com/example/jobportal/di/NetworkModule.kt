package com.example.jobportal.di

import com.example.jobportal.api.UserAPI
import com.example.jobportal.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)

                ///For checking all the network calls details via logging Interceptor
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providesUserApi(retrofit: Retrofit) :UserAPI{
        return retrofit.create(UserAPI::class.java)
    }
}
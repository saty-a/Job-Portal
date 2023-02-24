package com.example.jobportal.di

import com.example.jobportal.api.AuthInterceptor
import com.example.jobportal.api.JobsAPI
import com.example.jobportal.api.UserAPI
import com.example.jobportal.utils.Constants.BASE_URL
import com.example.jobportal.utils.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun providesUserApi(retofitBuilder: Retrofit.Builder): UserAPI {
        return retofitBuilder.build().create(UserAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesJobAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient,
        sessionManager: SessionManager
    ): JobsAPI {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(AuthInterceptor(sessionManager)).build()
        return retrofitBuilder
            .client(okHttpClient)
            .client(client)
            .build().create(JobsAPI::class.java)
    }

}
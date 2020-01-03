package com.erikriosetiawan.moviecatalogue.network

import com.erikriosetiawan.moviecatalogue.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkConfig {

    fun getIntercetor(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    fun getNetwork(): Retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .client(getIntercetor())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun api(): ApiService = getNetwork().create(ApiService::class.java)
}
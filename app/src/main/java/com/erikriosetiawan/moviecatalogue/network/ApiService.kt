package com.erikriosetiawan.moviecatalogue.network

import com.erikriosetiawan.moviecatalogue.BuildConfig
import com.erikriosetiawan.moviecatalogue.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("3/discover/movie?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getMovies(): Call<MovieResponse>
}
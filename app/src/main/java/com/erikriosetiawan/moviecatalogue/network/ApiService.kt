package com.erikriosetiawan.moviecatalogue.network

import com.erikriosetiawan.moviecatalogue.BuildConfig
import com.erikriosetiawan.moviecatalogue.models.MovieResponse
import com.erikriosetiawan.moviecatalogue.models.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("3/discover/movie?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getMovies(): Call<MovieResponse>

    @GET("3/discover/tv?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getTvShows(): Call<TvShowResponse>
}
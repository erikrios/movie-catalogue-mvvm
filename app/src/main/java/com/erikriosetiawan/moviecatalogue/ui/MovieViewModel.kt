package com.erikriosetiawan.moviecatalogue.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erikriosetiawan.moviecatalogue.models.Movie
import com.erikriosetiawan.moviecatalogue.models.MovieResponse
import com.erikriosetiawan.moviecatalogue.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private var movies = MutableLiveData<List<Movie>>()
    private var isFailed = MutableLiveData<Boolean>()

    private val LOG_TAG = MovieViewModel::class.java.simpleName

    init {
        setMovies()
    }

    private fun setMovies() {
        isFailed.value = true

        NetworkConfig().api().getMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isFailed.value = true
                movies.value = null
                Log.d(LOG_TAG, "Failed to fetch data because ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    isFailed.value = false
                    movies.value = response.body()?.movies
                    Log.d(LOG_TAG, "Fetch data success with response code ${response.code()}")
                } else isFailed.value = true
            }

        })
    }

    fun getMovies(): MutableLiveData<List<Movie>> = movies


    fun getIsFailed(): MutableLiveData<Boolean> = isFailed

}
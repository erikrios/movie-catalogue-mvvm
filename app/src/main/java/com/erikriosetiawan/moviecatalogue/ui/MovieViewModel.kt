package com.erikriosetiawan.moviecatalogue.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erikriosetiawan.moviecatalogue.models.MovieResponse
import com.erikriosetiawan.moviecatalogue.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private var movies = MutableLiveData<MovieResponse>()
    private var isFailed = MutableLiveData<Boolean>()

    init {
        setMovies()
    }

    private fun setMovies() {
        isFailed.value = true

        NetworkConfig().api().getMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isFailed.value = true
                movies.value = null
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                isFailed.value = false

                if (response.isSuccessful) movies.value = response.body()
                else isFailed.value = true
            }

        })
    }

    fun getMovies(): MutableLiveData<MovieResponse> {
        return movies
    }

    fun getIsFailed(): MutableLiveData<Boolean> {
        isFailed.value = true
        return isFailed
    }
}

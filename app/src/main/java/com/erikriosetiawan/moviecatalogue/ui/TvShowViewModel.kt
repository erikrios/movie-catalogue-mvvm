package com.erikriosetiawan.moviecatalogue.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erikriosetiawan.moviecatalogue.models.TvShow
import com.erikriosetiawan.moviecatalogue.models.TvShowResponse
import com.erikriosetiawan.moviecatalogue.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel : ViewModel() {

    private var tvShows = MutableLiveData<List<TvShow>>()
    private var isFailed = MutableLiveData<Boolean>()

    private val LOG_TAG = TvShowViewModel::class.java.simpleName

    init {
        setTvShows()
    }

    private fun setTvShows() {
        isFailed.value = true

        NetworkConfig().api().getTvShows().enqueue(object : Callback<TvShowResponse> {
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                isFailed.value = true
                tvShows.value = null
                Log.d(LOG_TAG, "Failed to fetch data because ${t.localizedMessage}")
            }

            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    isFailed.value = false
                    tvShows.value = response.body()?.tvShows
                    Log.d(LOG_TAG, "Fetch data success with response code ${response.code()}")
                } else isFailed.value = true
            }

        })
    }

    fun getTvShows(): MutableLiveData<List<TvShow>> = tvShows

    fun getIsFailed(): MutableLiveData<Boolean> = isFailed
}
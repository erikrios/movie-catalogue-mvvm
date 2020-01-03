package com.erikriosetiawan.moviecatalogue.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.moviecatalogue.R
import com.erikriosetiawan.moviecatalogue.databinding.ActivityDetailsBinding
import com.erikriosetiawan.moviecatalogue.models.Movie
import com.erikriosetiawan.moviecatalogue.models.TvShow

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var movie: Movie
    private lateinit var tvShow: TvShow
    private lateinit var dataKey: String

    companion object {
        const val DATA_KEY = "data_key"
        const val MOVIE_KEY = "movie_key"
        const val TV_SHOW_KEY = "tv_show_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
    }


}

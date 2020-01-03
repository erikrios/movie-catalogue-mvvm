package com.erikriosetiawan.moviecatalogue.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.moviecatalogue.R
import com.erikriosetiawan.moviecatalogue.databinding.ActivityDetailsBinding
import com.erikriosetiawan.moviecatalogue.models.Movie
import com.erikriosetiawan.moviecatalogue.models.TvShow
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var movie: Movie
    private lateinit var tvShow: TvShow

    companion object {
        const val DATA_KEY = "data_key"
        const val MOVIE_KEY = "movie_key"
        const val TV_SHOW_KEY = "tv_show_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        getDataIntent()
    }

    private fun getDataKey(): String? = intent.getStringExtra(DATA_KEY)

    private fun getDataIntent() {
        if (getDataKey() == MOVIE_KEY) {
            movie = intent.getParcelableExtra(MOVIE_KEY) as Movie
            Picasso.get().load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(binding.poster)
            binding.title.text = movie.title
            binding.firstAirDate.text = movie.releaseDate
            binding.overview.text = movie.overview
        } else {
            tvShow = intent.getParcelableExtra(TV_SHOW_KEY) as TvShow
            Picasso.get().load("https://image.tmdb.org/t/p/w500${tvShow.posterPath}")
                .into(binding.poster)
            binding.title.text = tvShow.title
            binding.firstAirDate.text = tvShow.firstAirDate
            binding.overview.text = tvShow.overview
        }
    }
}

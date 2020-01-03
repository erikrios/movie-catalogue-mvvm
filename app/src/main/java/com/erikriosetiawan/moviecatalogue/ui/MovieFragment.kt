package com.erikriosetiawan.moviecatalogue.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.moviecatalogue.R
import com.erikriosetiawan.moviecatalogue.databinding.FragmentMovieBinding
import com.erikriosetiawan.moviecatalogue.models.Movie
import com.erikriosetiawan.moviecatalogue.utils.LOG_TAG
import com.erikriosetiawan.moviecatalogue.utils.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBarMovie.visibility = View.VISIBLE

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        viewModel.getIsFailed().observe(this, Observer {
            if (it) {
                binding.progressBarMovie.visibility = View.GONE
                binding.noInternetConnection.visibility = View.VISIBLE
                Log.d(LOG_TAG, "Failed to fetch data")
            }
        })
        viewModel.getMovies().observe(this, Observer {
            binding.progressBarMovie.visibility = View.GONE
            setRecyclerView(it)
            Log.d(LOG_TAG, "Success to fetch data")
        })
    }

    private fun setRecyclerView(movies: List<Movie>) {
        binding.recyclerViewMovie.adapter =
            MovieAdapter(binding.root.context, movies as MutableList<Movie>)
        binding.recyclerViewMovie.layoutManager =
            LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
    }
}

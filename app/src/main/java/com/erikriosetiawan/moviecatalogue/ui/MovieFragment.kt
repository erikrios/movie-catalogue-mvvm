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
import com.erikriosetiawan.moviecatalogue.utils.MovieAdapter

class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: FragmentMovieBinding
    private var isEmpty: Boolean = true

    private val LOG_TAG = MovieFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null)
            isEmpty = savedInstanceState.getBoolean("data")

        if (isEmpty)
            binding.progressBarMovie.visibility = View.VISIBLE

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        viewModel.getIsFailed().observe(this, Observer {
            if (it) {
                Log.d(LOG_TAG, "Failed to fetch data")
            }
        })
        viewModel.getMovies().observe(this, Observer {
            binding.progressBarMovie.visibility = View.GONE
            setRecyclerView(it)
            isEmpty = false
            Log.d(LOG_TAG, "Success to fetch data")
        })
    }

    private fun setRecyclerView(movies: List<Movie>) {
        binding.recyclerViewMovie.adapter =
            MovieAdapter(binding.root.context, movies as MutableList<Movie>)
        binding.recyclerViewMovie.layoutManager =
            LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("data", isEmpty)
    }
}

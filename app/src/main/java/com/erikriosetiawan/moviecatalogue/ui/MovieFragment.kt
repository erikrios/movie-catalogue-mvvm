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
    private val movies: MutableList<Movie> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        viewModel.getIsFailed().observe(this, Observer {
            if (it) print("Failed to fetch data") else print("Success to fetch data")

        })
        viewModel.getMovies().observe(this, Observer {
//            movies.addAll(it)
//            Log.d("TES123", it[0].title)
        })
        // TODO: Use the ViewModel
//        setData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerViewMovie.adapter = MovieAdapter(binding.root.context, movies)
        binding.recyclerViewMovie.layoutManager =
            LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
    }
}

package com.erikriosetiawan.moviecatalogue.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
        // TODO: Use the ViewModel
        setData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerViewMovie.adapter = MovieAdapter(binding.root.context, movies)
        binding.recyclerViewMovie.layoutManager =
            LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
    }

    private fun setData() {
        for (i in 0..10) {
            movies.add(
                Movie(
                    i,
                    i,
                    "/jyw8VKYEiM1UDzPB7NsisUgBeJ8.jpg",
                    "Movie Title $i",
                    4.5 + i.toDouble(),
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    "2019-10-02"
                )
            )
        }
    }
}

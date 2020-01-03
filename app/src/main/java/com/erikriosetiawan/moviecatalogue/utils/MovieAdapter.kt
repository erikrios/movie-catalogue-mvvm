package com.erikriosetiawan.moviecatalogue.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.moviecatalogue.databinding.ItemMovieBinding
import com.erikriosetiawan.moviecatalogue.models.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(private val context: Context, private val movies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemBinding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(movies[position]) {
            // Intent code here
        }
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindViews(movie: Movie, listener: (Movie) -> Unit) {
            binding.movie = movie
            Picasso.get().load("https://image.tmdb.org/t/p/w185${movie.posterPath}")
                .into(binding.poster)
            binding.root.setOnClickListener { listener(movie) }
        }
    }

}
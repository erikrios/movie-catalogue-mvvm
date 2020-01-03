package com.erikriosetiawan.moviecatalogue.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.moviecatalogue.databinding.ItemTvShowBinding
import com.erikriosetiawan.moviecatalogue.models.TvShow
import com.erikriosetiawan.moviecatalogue.ui.DetailsActivity
import com.squareup.picasso.Picasso

class TvShowAdapter(private val context: Context, private val tvShows: MutableList<TvShow>) :
    RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemBinding = ItemTvShowBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(tvShows[position]) {
            val dataIntent = Intent(context, DetailsActivity::class.java)
            dataIntent.putExtra(DetailsActivity.DATA_KEY, DetailsActivity.TV_SHOW_KEY)
            dataIntent.putExtra(DetailsActivity.TV_SHOW_KEY, it)
            context.startActivity(dataIntent)
        }
    }

    inner class ViewHolder(private val binding: ItemTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViews(tvShow: TvShow, listener: (TvShow) -> Unit) {
            binding.tvShow = tvShow
            Picasso.get().load("https://image.tmdb.org/t/p/w185${tvShow.posterPath}")
                .into(binding.poster)
            binding.root.setOnClickListener { listener(tvShow) }
        }
    }
}
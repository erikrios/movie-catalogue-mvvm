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
import com.erikriosetiawan.moviecatalogue.databinding.FragmentTvShowBinding
import com.erikriosetiawan.moviecatalogue.models.TvShow
import com.erikriosetiawan.moviecatalogue.utils.TvShowAdapter

class TvShowFragment : Fragment() {

    companion object {
        fun newInstance() = TvShowFragment()
    }

    private lateinit var viewModel: TvShowViewModel
    private lateinit var binding: FragmentTvShowBinding
    private var isEmpty: Boolean = true

    private val LOG_TAG = TvShowFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null)
            isEmpty = savedInstanceState.getBoolean("data")

        if (isEmpty)
            binding.progressBarTvShow.visibility = View.VISIBLE

        viewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)

        viewModel.getIsFailed().observe(this, Observer {
            if (it) {
                Log.d(LOG_TAG, "Failed to fetch data")
            }
        })
        viewModel.getTvShows().observe(this, Observer {
            binding.progressBarTvShow.visibility = View.GONE
            setRecycerView(it)
            isEmpty = false
            Log.d(LOG_TAG, "Success to fetch data")
        })
    }

    private fun setRecycerView(tvShows: List<TvShow>) {
        binding.recyclerViewTvShow.adapter =
            TvShowAdapter(binding.root.context, tvShows as MutableList<TvShow>)
        binding.recyclerViewTvShow.layoutManager =
            LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("data", isEmpty)
    }
}

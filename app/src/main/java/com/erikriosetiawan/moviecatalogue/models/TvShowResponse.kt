package com.erikriosetiawan.moviecatalogue.models

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @SerializedName("page")
    var page: Int,

    @SerializedName("total_results")
    var totalResults: Int,

    @SerializedName("total_pages")
    var totalPages: Int,

    @SerializedName("results")
    var tvShows: List<TvShow>
)
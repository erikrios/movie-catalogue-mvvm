package com.erikriosetiawan.moviecatalogue.models

import com.google.gson.annotations.SerializedName

data class TvShow(

    @SerializedName("popularity")
    var popularity: Double,

    @SerializedName("vote_count")
    var voteCount: Double,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("name")
    var title: String,

    @SerializedName("vote_average")
    var voteAverage: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("first_air_date")
    var firstAirDate: String
)
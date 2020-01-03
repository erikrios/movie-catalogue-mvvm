package com.erikriosetiawan.moviecatalogue.models

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("popularity")
    var popularity: Int,

    @SerializedName("vote_count")
    var voteCount: Int,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("vote_average")
    var voteAverage: Double,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("release_date")
    var releaseDate: String
)
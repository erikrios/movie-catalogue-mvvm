package com.erikriosetiawan.moviecatalogue.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    @SerializedName("popularity")
    var popularity: Double,

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
) : Parcelable
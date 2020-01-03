package com.erikriosetiawan.moviecatalogue.models

data class Movie(
    var popularity: Int,
    var voteCount: Int,
    var posterPath: String,
    var title: String,
    var voteAverage: Double,
    var overview: String,
    var releaseDate: String
)
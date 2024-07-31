package com.patan.tmdbapp.model


import com.google.gson.annotations.SerializedName

data class SearchItem(
    @SerializedName("genre_ids")
    val genreİds: List<Int?>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
)
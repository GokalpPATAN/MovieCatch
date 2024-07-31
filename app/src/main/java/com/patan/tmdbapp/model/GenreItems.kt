package com.patan.tmdbapp.model


import com.google.gson.annotations.SerializedName

data class GenreItems(
    @SerializedName("genres")
    val genreItems: List<Genre?>?,
)
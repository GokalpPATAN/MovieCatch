package com.patan.tmdbapp.model


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("results")
    val Items: List<Item?>?,
)
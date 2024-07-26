package com.patan.tmdbapp.model


import com.google.gson.annotations.SerializedName

data class RatedResponse(
    @SerializedName("results")
    val ratedItems: List<RatedItem?>?,
)
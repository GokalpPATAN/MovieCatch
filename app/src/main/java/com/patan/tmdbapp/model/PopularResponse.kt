package com.patan.tmdbapp.model


import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("results")
    val popularItems: List<PopularItem?>?,
)
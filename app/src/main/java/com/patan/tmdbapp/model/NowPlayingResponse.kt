package com.patan.tmdbapp.model


import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(
    @SerializedName("results")
    val nowPlayingItems: List<NowPlayingItem?>?,
)
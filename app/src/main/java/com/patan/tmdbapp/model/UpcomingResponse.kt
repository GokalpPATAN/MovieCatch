package com.patan.tmdbapp.model


import com.google.gson.annotations.SerializedName

data class UpcomingResponse(
    @SerializedName("results")
    val upcomingItems: List<UpcomingItem?>?,
)
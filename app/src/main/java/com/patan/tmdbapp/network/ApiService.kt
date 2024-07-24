package com.patan.tmdbapp.network

import com.patan.tmdbapp.model.PopularResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("popular")
    suspend fun getPopular():Response<PopularResponse>
}
package com.patan.tmdbapp.network

import com.patan.tmdbapp.model.PopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("popular")
    suspend fun getPopular(@Header("Authorization") token : String):Response<PopularResponse>
}
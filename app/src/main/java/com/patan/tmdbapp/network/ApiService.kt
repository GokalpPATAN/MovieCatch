package com.patan.tmdbapp.network

import com.patan.tmdbapp.model.NowPlayingResponse
import com.patan.tmdbapp.model.PopularResponse
import com.patan.tmdbapp.model.RatedResponse
import com.patan.tmdbapp.model.UpcomingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("popular")
    suspend fun getPopular(@Header("Authorization") token : String):Response<PopularResponse>
    @GET("now_playing")
    suspend fun getNow(@Header("Authorization") token: String):Response<NowPlayingResponse>
    @GET("top_rated")
    suspend fun getTop(@Header("Authorization") token: String):Response<RatedResponse>
    @GET("upcoming")
    suspend fun getUp(@Header("Authorization")token: String):Response<UpcomingResponse>
}
package com.patan.tmdbapp.network

import com.patan.tmdbapp.model.DetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getPopular(@Header("Authorization") token: String): Response<com.patan.tmdbapp.model.Response>

    @GET("3/movie/now_playing")
    suspend fun getNow(@Header("Authorization") token: String): Response<com.patan.tmdbapp.model.Response>

    @GET("3/movie/top_rated")
    suspend fun getTop(@Header("Authorization") token: String): Response<com.patan.tmdbapp.model.Response>

    @GET("3/movie/upcoming")
    suspend fun getUp(@Header("Authorization") token: String): Response<com.patan.tmdbapp.model.Response>

    @GET("3/movie/{movieId}")
    suspend fun getDetails(
        @Path("movieId") movieId: String,
        @Header("Authorization") token: String
    ): Response<com.patan.tmdbapp.model.Response>


    @GET("3/search/movie")
    suspend fun getSearch(
        @Query("query") queryString: String,@Header("Authorization") token: String
    ): Response<com.patan.tmdbapp.model.Response>
}
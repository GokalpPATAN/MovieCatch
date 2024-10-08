package com.patan.tmdbapp.network

import com.patan.tmdbapp.model.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/{topath}")
    suspend fun getPopular(
        @Path("topath") topath: String,
        @Header("Authorization") token: String,
        @Query("page") page: Int,
    ): Response<com.patan.tmdbapp.model.Response>

    @GET("3/movie/{movieId}")
    suspend fun getDetails(
        @Path("movieId") movieId: String,
        @Header("Authorization") token: String
    ): Response<Item>

    @GET("3/movie/{movieId}")
    suspend fun getMovieIds(
        @Path("movieId") movieId: String,
        @Header("Authorization") token: String
    ): Response<Item>

    @GET("3/search/movie")
    suspend fun getSearch(
        @Query("query") queryString: String,
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): Response<com.patan.tmdbapp.model.Response>

    @GET("3/discover/movie?with_origin_country=TR")
    suspend fun getTV(
        @Query("page") page: Int,
        @Header("Authorization") token: String
    ): Response<com.patan.tmdbapp.model.Response>
}
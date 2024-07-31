package com.patan.tmdbapp.network

import com.patan.tmdbapp.model.DetailsResponse
import com.patan.tmdbapp.model.GenreItems
import com.patan.tmdbapp.model.NowPlayingResponse
import com.patan.tmdbapp.model.PopularResponse
import com.patan.tmdbapp.model.RatedResponse
import com.patan.tmdbapp.model.SearchResponse
import com.patan.tmdbapp.model.UpcomingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getPopular(@Header("Authorization") token: String): Response<PopularResponse>

    @GET("3/movie/now_playing")
    suspend fun getNow(@Header("Authorization") token: String): Response<NowPlayingResponse>

    @GET("3/movie/top_rated")
    suspend fun getTop(@Header("Authorization") token: String): Response<RatedResponse>

    @GET("3/movie/upcoming")
    suspend fun getUp(@Header("Authorization") token: String): Response<UpcomingResponse>

    @GET("3/movie/{movieId}")
    suspend fun getDetails(
        @Path("movieId") movieId: String,
        @Header("Authorization") token: String
    ): Response<DetailsResponse>

    @GET("3/movie/{movieId}")
    suspend fun getGenre(
        @Path("movieId") movieId: String,
        @Header("Authorization") token: String
    ): Response<GenreItems>


    @GET("3/search/movie")
    suspend fun getSearch(
        @Query("query") queryString: String,@Header("Authorization") token: String
    ): Response<SearchResponse>
}
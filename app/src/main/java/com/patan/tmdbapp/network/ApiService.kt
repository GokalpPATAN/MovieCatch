package com.patan.tmdbapp.network

import com.patan.tmdbapp.model.DetailsResponse
import com.patan.tmdbapp.model.Genre
import com.patan.tmdbapp.model.NowPlayingResponse
import com.patan.tmdbapp.model.PopularResponse
import com.patan.tmdbapp.model.RatedResponse
import com.patan.tmdbapp.model.SearchItem
import com.patan.tmdbapp.model.UpcomingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("popular")
    suspend fun getPopular(@Header("Authorization") token : String):Response<PopularResponse>
    @GET("now_playing")
    suspend fun getNow(@Header("Authorization") token: String):Response<NowPlayingResponse>
    @GET("top_rated")
    suspend fun getTop(@Header("Authorization") token: String):Response<RatedResponse>
    @GET("upcoming")
    suspend fun getUp(@Header("Authorization")token: String):Response<UpcomingResponse>
    @GET("{movieId}")
    suspend fun getDetails(@Path("movieId") movieId: String, @Header("Authorization")token: String):Response<DetailsResponse>

    @GET("{movieId}")
    suspend fun getGenre(@Path("movieId") movieId: String, @Header("Authorization")token: String):Response<Genre>



    @GET("3/search/movie")
    suspend fun getSearch(
        @Query("query") queryString: String,
        @Query("api_key") apiKey: String = "cf9fa167ff120c66726a8a319bff71f1"): Response<SearchItem>
}
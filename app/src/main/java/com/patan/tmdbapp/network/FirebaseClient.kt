package com.patan.tmdbapp.network

interface FirebaseClient {
    fun addFavourite(movieId: Int, userEmail: String)
    fun deleteFavourite(movieId: Int, userEmail: String)
    fun isFavourite(movieId: Int, userEmail: String, callback: (Boolean) -> Unit)
    fun getMovieId(userEmail: String, callback: (List<String>) -> Unit)
}

package com.patan.tmdbapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.network.FirebaseClient
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch

class DetailsViewModel(private val firebaseClient: FirebaseClient) : ViewModel() {
    val detailList: MutableLiveData<Item?> = MutableLiveData()
    val errorMessage: MutableLiveData<String?> = MutableLiveData()
    val isFavourite: MutableLiveData<Boolean> = MutableLiveData()
    val movieIds: MutableLiveData<List<String>> = MutableLiveData()
    val IdsList: MutableLiveData<Item?> = MutableLiveData()
    val commentList: MutableLiveData<List<String>> = MutableLiveData()
    val userName: MutableLiveData<List<String>> = MutableLiveData()
    
    fun getDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getClient()
                    .getDetails(movieId = movieId.toString(), token = Constants.BEARER_KEY)
                if (response.isSuccessful) {
                    detailList.postValue(response.body())
                } else {
                    errorMessage.value =
                        response.message().takeIf { it.isNotEmpty() } ?: "An unknown error occurred"
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

    fun getMoviesFromApi(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getClient()
                    .getMovieIds(movieId = movieId.toString(), token = Constants.BEARER_KEY)
                if (response.isSuccessful) {
                    IdsList.postValue(response.body())
                } else {
                    errorMessage.value =
                        response.message().takeIf { it.isNotEmpty() } ?: "An unknown error occurred"
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

    fun addFavourite(movieId: Int, userEmail: String) {
        firebaseClient.addFavourite(movieId, userEmail)
    }

    fun deleteFavourite(movieId: Int, userEmail: String) {
        firebaseClient.deleteFavourite(movieId, userEmail)
    }

    fun checkFavourite(movieId: Int, userEmail: String) {
        firebaseClient.isFavourite(movieId, userEmail) { isFav ->
            isFavourite.postValue(isFav)
        }
    }

    fun getMovieIdsFromDatabase(userEmail: String) {
        firebaseClient.getMovieId(userEmail) { it ->
            movieIds.postValue(it)
        }
    }

    fun addComment(movieName: String, userEmail: String, comment: String) {
        firebaseClient.addComment(movieName, userEmail, comment)
    }

    fun getCommentsFromDatabase(movieName: String) {
        firebaseClient.getComment(movieName) { comment, user ->
            commentList.postValue(comment)
            userName.postValue(user)
        }
    }
}

package com.patan.tmdbapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.DetailsResponse
import com.patan.tmdbapp.model.Genre
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    val detailList: MutableLiveData<DetailsResponse> = MutableLiveData()
    val errorMesage: MutableLiveData<String?> = MutableLiveData()
    val genreList: MutableLiveData<List<Genre?>?> = MutableLiveData()

    fun getDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getClient()
                    .getDetails(movieId = movieId.toString(), token = Constants.BEARER_DETAILS)
                if (response.isSuccessful) {
                    detailList.postValue(response.body())
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMesage.value = "An unknown error occured"
                    } else {
                        errorMesage.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMesage.value = e.message

            }
        }
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getClient()
                    .getGenre(movieId = movieId.toString(), token = Constants.BEARER_DETAILS)
                if (response.isSuccessful) {
                    genreList.postValue(response.body()?.genreItems)
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMesage.value = "An unknown error occured"
                    } else {
                        errorMesage.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMesage.value = e.message

            }
        }
    }

}

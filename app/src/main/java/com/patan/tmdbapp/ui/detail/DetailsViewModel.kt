package com.patan.tmdbapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.Genre
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch


class DetailsViewModel : ViewModel() {
    val genreList: MutableLiveData<List<Genre?>?> = MutableLiveData()
    val errorMesage: MutableLiveData<String?> = MutableLiveData()
    val loading = MutableLiveData(false)

    fun getDetails(movieId: Int) {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getClient()
                    .getDetails(movieId = movieId.toString(), token = Constants.BEARER_DETAILS)
                if (response.isSuccessful) {
                    genreList.postValue(response.body()?.genres)
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
            loading.value = false
        }
    }

}

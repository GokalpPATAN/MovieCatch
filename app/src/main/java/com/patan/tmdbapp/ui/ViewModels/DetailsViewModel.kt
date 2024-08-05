package com.patan.tmdbapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.Genre
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.model.Response
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch


class DetailsViewModel : ViewModel() {
    val detailList: MutableLiveData<Item?> = MutableLiveData()
    val errorMesage: MutableLiveData<String?> = MutableLiveData()

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
    }

}

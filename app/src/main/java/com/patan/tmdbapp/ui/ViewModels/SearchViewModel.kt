package com.patan.tmdbapp.ui.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val searchList: MutableLiveData<List<Item?>?> = MutableLiveData()
    val tvList: MutableLiveData<List<Item?>?> = MutableLiveData()
    val errorMesage: MutableLiveData<String?> = MutableLiveData()

    fun getSearch(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getClient()
                    .getSearch(queryString = query, token = Constants.BEARER_KEY)
                if (response.isSuccessful) {
                    searchList.postValue(response.body()?.Items)
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
    fun getTV(){
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getClient()
                    .getTV(token = Constants.BEARER_KEY)
                if (response.isSuccessful) {
                    tvList.postValue(response.body()?.Items)
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
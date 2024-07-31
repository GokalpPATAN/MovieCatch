package com.patan.tmdbapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.SearchItem
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val searchList: MutableLiveData<List<SearchItem?>?> = MutableLiveData()
    val errorMesage: MutableLiveData<String?> = MutableLiveData()

    fun getSearch(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getClient()
                    .getSearch(queryString = query, token = Constants.API_KEY)
                if (response.isSuccessful) {
                    searchList.postValue(response.body()?.searchItems)
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
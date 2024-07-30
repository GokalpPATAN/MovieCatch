package com.patan.tmdbapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.NowPlayingItem
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val nowList: MutableLiveData<List<NowPlayingItem?>?> = MutableLiveData()
    val errorMesage: MutableLiveData<String?> = MutableLiveData()

    init {
        getNow()
    }

    fun getNow() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getClient().getNow(token = Constants.BEARER_NOW)

                if (response.isSuccessful) {
                    nowList.postValue(response.body()?.nowPlayingItems)
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
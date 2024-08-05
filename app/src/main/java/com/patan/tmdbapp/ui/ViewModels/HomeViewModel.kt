package com.patan.tmdbapp.ui.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var list: MutableLiveData<List<Item?>?> = MutableLiveData()
    val errorMesage: MutableLiveData<String?> = MutableLiveData()

    fun getPopularList(topath: String?) {
        viewModelScope.launch {
            val responsePopular =
                RetrofitClient.getClient()
                    .getPopular(topath = topath.toString(), token = Constants.BEARER_POPULAR)

            try {
                if (responsePopular.isSuccessful) {
                    list.postValue(responsePopular.body()?.Items)
                } else {
                    if (responsePopular.message().isNullOrEmpty()) {
                        errorMesage.value = "An unknown error occured"
                    } else {
                        errorMesage.value = responsePopular.message()
                    }
                }
            } catch (e: Exception) {
                errorMesage.value = e.message

            }
        }
    }
}

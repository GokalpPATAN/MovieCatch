package com.patan.tmdbapp.ui.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch

class PopularViewModel : ViewModel() {
    var list: MutableLiveData<List<Item?>?> = MutableLiveData()
    val errorMesage: MutableLiveData<String?> = MutableLiveData()

    fun getPopularList() {
        viewModelScope.launch {
            val responsePopular =
                RetrofitClient.getClient().getPopular(token = Constants.BEARER_POPULAR)

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

    fun getNowList() {
        viewModelScope.launch {
            val responseNow = RetrofitClient.getClient().getNow(token = Constants.BEARER_NOW)
            try {
                if (responseNow.isSuccessful) {
                    list.postValue(responseNow.body()?.Items)
                } else {
                    if (responseNow.message().isNullOrEmpty()) {
                        errorMesage.value = "An unknown error occured"
                    } else {
                        errorMesage.value = responseNow.message()
                    }
                }
            } catch (e: Exception) {
                errorMesage.value = e.message

            }
        }
    }

    fun getTopList() {
        viewModelScope.launch {

            val responseTop = RetrofitClient.getClient().getTop(token = Constants.BEARER_TOP)


            try {
                if (responseTop.isSuccessful) {
                    list.postValue(responseTop.body()?.Items)
                } else {
                    if (responseTop.message().isNullOrEmpty()) {
                        errorMesage.value = "An unknown error occured"
                    } else {
                        errorMesage.value = responseTop.message()
                    }
                }
            } catch (e: Exception) {
                errorMesage.value = e.message

            }
        }
    }

    fun getUpList() {
        viewModelScope.launch {
            val responseUp = RetrofitClient.getClient().getUp(token = Constants.BEARER_UP)

            try {
                if (responseUp.isSuccessful) {
                    list.postValue(responseUp.body()?.Items)
                } else {
                    if (responseUp.message().isNullOrEmpty()) {
                        errorMesage.value = "An unknown error occured"
                    } else {
                        errorMesage.value = responseUp.message()
                    }
                }
            } catch (e: Exception) {
                errorMesage.value = e.message

            }
        }
    }


}

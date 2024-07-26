package com.patan.tmdbapp.ui.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.UpcomingItem
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch

class UpcomingViewModel:ViewModel() {
    val upList:MutableLiveData<List<UpcomingItem?>?> = MutableLiveData()
    val errorMesage:MutableLiveData<String?> = MutableLiveData()

init {
    getUp()
}

    fun getUp() {
    viewModelScope.launch {
        val response = RetrofitClient.getClient().getUp(token =Constants.BEARER_UP)
        try {
            if(response.isSuccessful){
                upList.postValue(response.body()?.upcomingItems)
            }
            else{
                if(response.message().isNullOrEmpty()){
                    errorMesage.value="An unknown error occured"
                }else{
                    errorMesage.value=response.message()
                }
            }
        }
        catch (e:Exception){
            errorMesage.value=e.message
        }
    }
    }
}

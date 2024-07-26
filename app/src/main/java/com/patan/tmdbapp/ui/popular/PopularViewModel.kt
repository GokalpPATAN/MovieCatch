package com.patan.tmdbapp.ui.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.PopularItem
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch

class PopularViewModel: ViewModel() {
    val popularList:MutableLiveData<List<PopularItem?>?> = MutableLiveData()
    val errorMesage:MutableLiveData<String?> = MutableLiveData()

    init {
        getPopular()
    }
    fun getPopular(){
        viewModelScope.launch {
            val response=RetrofitClient.getClient().getPopular(token = Constants.BEARER_POPULAR)
            try {
                if(response.isSuccessful){
                    popularList.postValue(response.body()?.popularItems)
                }
                else{
                    if (response.message().isNullOrEmpty()){
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
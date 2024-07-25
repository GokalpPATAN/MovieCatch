package com.patan.tmdbapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.PopularItem
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val popularList : MutableLiveData<List<PopularItem?>?> = MutableLiveData()
    val errorMesage: MutableLiveData<String?> = MutableLiveData()

    init {
        getPopularList()
    }

    fun getPopularList(){
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getClient().getPopular(token = Constants.BEARER)

                if(response.isSuccessful) {
                    popularList.postValue(response.body()?.popularItems)
                }else{
                    if (response.message().isNullOrEmpty()){
                        errorMesage.value="An unknown error occured"
                    } else{
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
package com.patan.tmdbapp.ui.top_rated

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patan.tmdbapp.model.RatedItem
import com.patan.tmdbapp.network.RetrofitClient
import com.patan.tmdbapp.util.Constants
import kotlinx.coroutines.launch

class RatedViewModel:ViewModel() {
    val ratedList : MutableLiveData<List<RatedItem?>?> = MutableLiveData()
    val errorMesage: MutableLiveData<String?> = MutableLiveData()

    init {
        getTop()
    }

    fun getTop() {
        viewModelScope.launch {
            try {
                val response=RetrofitClient.getClient().getTop(token = Constants.BEARER_TOP)
                if(response.isSuccessful){
                    ratedList.postValue(response.body()?.ratedItems)
                }else{
                if (response.message().isNullOrEmpty()){
                    errorMesage.value="An unknown error occured"
                }else{
                    errorMesage.value=response.message()
                }

                }

            }catch (e:Exception){
                errorMesage.value=e.message

            }
        }
    }


}
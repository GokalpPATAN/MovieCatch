package com.patan.tmdbapp.ui.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.network.ApiService
import com.patan.tmdbapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    val fabVisible = MutableLiveData<Boolean>(false)
    var list: MutableLiveData<List<Item?>?> = MutableLiveData()
    val errorMesage: MutableLiveData<String?> = MutableLiveData()

    @Inject
    lateinit var auth: FirebaseAuth


    fun getPopularList(topath: String?) {
        viewModelScope.launch {
            val responsePopular =
                apiService.getPopular(topath = topath.toString(), token = Constants.BEARER_KEY)

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

    fun signOut() {
        auth.signOut()
    }

    fun fabVisibility() {
        fabVisible.value = fabVisible.value?.not() ?: true
    }
}

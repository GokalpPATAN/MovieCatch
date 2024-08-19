package com.patan.tmdbapp.ui.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.firebase.auth.FirebaseAuth
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.network.ApiService
import com.patan.tmdbapp.pagination.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _pagingData = MutableLiveData<PagingData<Item>>()
    val pagingData: LiveData<PagingData<Item>> = _pagingData
    val fabVisible = MutableLiveData<Boolean>(false)
    var list: MutableLiveData<List<Item?>?> = MutableLiveData()

    @Inject
    lateinit var auth: FirebaseAuth

    fun getMoviesPagingData(topath: String) {
        viewModelScope.launch {
            Pager(config = PagingConfig(
                pageSize = 10, enablePlaceholders = false
            ), pagingSourceFactory = { MoviesPagingSource(apiService, topath) }).flow.cachedIn(
                viewModelScope
            ).collectLatest { pagingData ->
                _pagingData.postValue(pagingData)
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

package com.patan.tmdbapp.ui.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.network.ApiService
import com.patan.tmdbapp.pagination.MoviesPagingSource
import com.patan.tmdbapp.pagination.SearchPagingSource
import com.patan.tmdbapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _pagingData = MutableLiveData<PagingData<Item>>()
    val pagingData: LiveData<PagingData<Item>> = _pagingData

    fun getSearchPagingData(query:String) {
        viewModelScope.launch {
            Pager(config = PagingConfig(
                pageSize = 10, enablePlaceholders = false
            ), pagingSourceFactory = { SearchPagingSource(apiService, query) }).flow.cachedIn(
                viewModelScope
            ).collectLatest { pagingData ->
                _pagingData.postValue(pagingData)
            }
        }
    }
}
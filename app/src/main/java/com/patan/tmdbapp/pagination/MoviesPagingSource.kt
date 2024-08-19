package com.patan.tmdbapp.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.network.ApiService
import com.patan.tmdbapp.util.Constants.Companion.BEARER_KEY
import com.patan.tmdbapp.util.Constants.Companion.STARTING_PAGE_INDEX
import javax.inject.Inject

class MoviesPagingSource@Inject constructor(private val apiService: ApiService, private val topath:String) : PagingSource<Int, Item>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiService.getPopular(topath, BEARER_KEY, page)
            val list = response.body()?.Items?.filterNotNull() ?: emptyList()
            LoadResult.Page(
                data = list,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (list.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { it ->
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
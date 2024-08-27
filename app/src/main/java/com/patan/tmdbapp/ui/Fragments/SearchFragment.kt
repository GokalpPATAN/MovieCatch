package com.patan.tmdbapp.ui.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.patan.tmdbapp.databinding.FragmentSearchBinding
import com.patan.tmdbapp.ui.Fragments.BaseFragment
import com.patan.tmdbapp.ui.ViewModels.SearchViewModel
import com.patan.tmdbapp.ui.adapter.HomeAdapter
import com.patan.tmdbapp.ui.adapter.MovieClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var searchAdapter: HomeAdapter

    @Inject
    lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchAdapter = HomeAdapter(object : MovieClickListener {
            override fun onMovieClicked(movieId: Int?) {
                if (movieId != null) {
                    val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
                        movieId
                    )
                    findNavController().navigate(action)
                }
            }
        })
        binding.RecyclerView5.adapter = searchAdapter
        viewModel.getSearchView()
        viewModel.pagingData2.observe(viewLifecycleOwner) { pagingData ->
            searchAdapter.submitData(lifecycle, pagingData)
        }
        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query1: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query?.length!! < 3) {

                } else if (query?.length!! >= 3) {
                    val query = binding.searchview.query.toString()
                    binding.RecyclerView5.adapter = searchAdapter
                    viewModel.getSearchPagingData(query)
                    observeEvents()
                }
                return true
            }

        })

    }

    private fun observeEvents() {
        viewModel.pagingData.observe(viewLifecycleOwner) { pagingData ->
            searchAdapter.submitData(lifecycle, pagingData)
        }

    }
}
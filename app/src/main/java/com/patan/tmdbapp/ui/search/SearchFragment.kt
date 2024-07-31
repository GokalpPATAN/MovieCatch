package com.patan.tmdbapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.patan.tmdbapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query1: String?): Boolean {
                val query = binding.searchview.query
                viewModel.getSearch(query = query.toString())
                observeEvents()

                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {

                return false
            }

        })
        val query = binding.searchview.query
        viewModel.getSearch(query = query.toString())
        observeEvents()
        return binding.root
    }

    private fun observeEvents() {
        viewModel.searchList.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {

            } else {
                searchAdapter = SearchAdapter(list)
                binding.RecyclerView5.adapter = searchAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
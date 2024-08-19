package com.patan.tmdbapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.patan.tmdbapp.databinding.FragmentHomeBinding
import com.patan.tmdbapp.ui.ViewModels.HomeViewModel
import com.patan.tmdbapp.ui.adapter.HomeAdapter
import com.patan.tmdbapp.ui.adapter.MovieClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var popularListAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val responsePath = arguments?.getString("topath") ?: "now_playing"
        popularListAdapter = HomeAdapter(object : MovieClickListener {
            override fun onMovieClicked(movieId: Int?) {
                if (movieId != null) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId)
                    findNavController().navigate(action)
                }
            }
        })
        binding.recyclerView3.adapter = popularListAdapter
        viewModel.getMoviesPagingData(topath = responsePath)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.pagingData.observe(viewLifecycleOwner) { pagingData ->
            popularListAdapter.submitData(lifecycle, pagingData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

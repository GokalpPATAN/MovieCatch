package com.patan.tmdbapp.ui.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.patan.tmdbapp.databinding.FragmentHomeBinding
import com.patan.tmdbapp.ui.ViewModels.HomeViewModel
import com.patan.tmdbapp.ui.adapter.HomeAdapter
import com.patan.tmdbapp.ui.adapter.MovieClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var popularListAdapter: HomeAdapter

    @Inject
    lateinit var auth: FirebaseAuth

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
}

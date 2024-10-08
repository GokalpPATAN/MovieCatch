package com.patan.tmdbapp.ui.Fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.patan.tmdbapp.databinding.FragmentUserSettingsBinding
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.ui.adapter.FavAdapter
import com.patan.tmdbapp.ui.adapter.MovieClickListener
import com.patan.tmdbapp.ui.detail.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserSettingsFragment :
    BaseFragment<FragmentUserSettingsBinding>(FragmentUserSettingsBinding::inflate) {
    @Inject
    lateinit var auth: FirebaseAuth
    private lateinit var favListAdapter: FavAdapter

    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.getMovieIdsFromDatabase(userEmail = auth.currentUser?.email ?: "")
        viewModel.movieIds.observe(viewLifecycleOwner) {
            it.forEach {
                val id = it.toInt()
                viewModel.getMoviesFromApi(id)
            }
            val allItemsList = mutableListOf<Item?>()
            viewModel.IdsList.observe(viewLifecycleOwner) {
                if (it != null) {
                    allItemsList.add(it)
                }
                println(it)
                favListAdapter = FavAdapter(allItemsList, object : MovieClickListener {
                    override fun onMovieClicked(movieId: Int?) {
                        if (movieId != null) {
                            val action =
                                UserSettingsFragmentDirections.actionUserSettingsFragmentToDetailsFragment(
                                    movieId
                                )
                            findNavController().navigate(action)
                        }
                    }
                })
                binding.progressBar.isVisible = false
                binding.recyclerView4.adapter = favListAdapter
            }

        }
    }
}
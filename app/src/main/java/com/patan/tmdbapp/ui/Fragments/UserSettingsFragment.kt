package com.patan.tmdbapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.patan.tmdbapp.databinding.FragmentUserSettingsBinding
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.network.FirebaseClientImpl
import com.patan.tmdbapp.ui.adapter.MainAdapter
import com.patan.tmdbapp.ui.adapter.MovieClickListener
import com.patan.tmdbapp.ui.detail.DetailsViewModel

class UserSettingsFragment : Fragment() {

    private var _binding: FragmentUserSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var popularListAdapter: MainAdapter
    private lateinit var auth: FirebaseAuth


    private val viewModel: DetailsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                    return DetailsViewModel(FirebaseClientImpl()) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
                popularListAdapter = MainAdapter(allItemsList, object : MovieClickListener {
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
                binding.recyclerView4.adapter = popularListAdapter
            }

        }
    }
}
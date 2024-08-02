package com.patan.tmdbapp.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.patan.tmdbapp.databinding.FragmentUpcomingBinding
import com.patan.tmdbapp.ui.adapter.MainAdapter
import com.patan.tmdbapp.ui.adapter.MovieClickListener
import com.patan.tmdbapp.ui.popular.PopularViewModel

class UpcomingFragment : Fragment() {
    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PopularViewModel>()
    private lateinit var upcomingAdapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)



        return binding.root
    }

    private fun observeEvents() {
        viewModel.list.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
            } else {
                upcomingAdapter = MainAdapter(list, object : MovieClickListener {
                    override fun onMovieClicked(movieId: Int?) {
                        if (movieId != null) {
                            val action =
                                UpcomingFragmentDirections.actionUpcomingFragmentToDetailsFragment(
                                    movieId
                                )
                            findNavController().navigate(action)
                        }
                    }
                })
                binding.recyclerView.adapter = upcomingAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUpList()
        observeEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
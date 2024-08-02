package com.patan.tmdbapp.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.patan.tmdbapp.databinding.FragmentPopularBinding
import com.patan.tmdbapp.ui.adapter.MainAdapter
import com.patan.tmdbapp.ui.adapter.MovieClickListener


class PopularFragment : Fragment() {
    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PopularViewModel>()
    private lateinit var popularListAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun observeEvents() {
        viewModel.list.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
            } else {
                popularListAdapter = MainAdapter(list, object : MovieClickListener {
                    override fun onMovieClicked(movieId: Int?) {
                        if(movieId !=null) {
                            val action =
                                PopularFragmentDirections.actionPopularFragmentToDetailsFragment(movieId)
                            findNavController().navigate(action)
                        }
                    }
                })
                binding.recyclerView3.adapter = popularListAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPopularList()
        observeEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
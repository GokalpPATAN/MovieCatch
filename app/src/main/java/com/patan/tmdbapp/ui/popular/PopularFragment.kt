package com.patan.tmdbapp.ui.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.patan.tmdbapp.databinding.FragmentPopularBinding


class PopularFragment : Fragment() {
    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PopularViewModel>()
    private lateinit var popularListAdapter: PopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)

        viewModel.getPopular()
        observeEvents()
        return binding.root
    }

    private fun observeEvents() {
        viewModel.popularList.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
            } else {
                popularListAdapter = PopularAdapter(list)
                binding.recyclerView3.adapter = popularListAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
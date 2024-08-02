package com.patan.tmdbapp.ui.top_rated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.patan.tmdbapp.databinding.FragmentRatedBinding
import com.patan.tmdbapp.ui.adapter.MainAdapter
import com.patan.tmdbapp.ui.adapter.MovieClickListener
import com.patan.tmdbapp.ui.popular.PopularViewModel


class RatedFragment : Fragment() {
    private var _binding: FragmentRatedBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PopularViewModel>()
    private lateinit var topListAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRatedBinding.inflate(inflater, container, false)



        return binding.root

    }

    private fun observeEvents() {
        viewModel.list.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {

            } else {
                topListAdapter = MainAdapter(list, object : MovieClickListener {
                    override fun onMovieClicked(movieId: Int?) {
                        if(movieId !=null) {
                            val action =
                                RatedFragmentDirections.actionRatedFragmentToDetailsFragment(movieId)
                            findNavController().navigate(action)
                        }
                    }

                })
                binding.recyclerView4.adapter = topListAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopList()
        observeEvents()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
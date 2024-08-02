package com.patan.tmdbapp.ui.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.patan.tmdbapp.databinding.FragmentHomeBinding
import com.patan.tmdbapp.ui.adapter.MainAdapter
import com.patan.tmdbapp.ui.adapter.MovieClickListener
import com.patan.tmdbapp.ui.popular.PopularViewModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PopularViewModel>()
    private lateinit var nowListAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)



        return binding.root
    }

    private fun observeEvents() {
        viewModel.list.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
            } else {
                nowListAdapter = MainAdapter(list, object : MovieClickListener {
                    override fun onMovieClicked(movieId: Int?) {
                        if(movieId !=null) {
                            val action =
                                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId)
                            findNavController().navigate(action)
                        }
                    }
                })
                binding.recyclerView2.adapter = nowListAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNowList()
        observeEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
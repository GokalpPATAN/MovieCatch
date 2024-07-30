package com.patan.tmdbapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.patan.tmdbapp.databinding.FragmentDetailsBinding
import com.patan.tmdbapp.util.loadCircleImage

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailsViewModel>()
    private lateinit var genreListAdapter: DetailsAdapter
    private val args by navArgs<DetailsFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToHomeFragment()
            findNavController().navigate(action)
        }
        viewModel.getDetails(movieId = args.movieId)
        // observeGenre()
        observeEvents()
        return binding.root
    }

    private fun observeEvents() {
        viewModel.genreList.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
            } else {
                genreListAdapter = DetailsAdapter(list)
                binding.recyclerView3.adapter = genreListAdapter
            }

        }
        viewModel.detailList.observe(viewLifecycleOwner) { movie ->
            binding.textView.text = movie.title
            binding.textView2.text = movie.overview
            binding.imageView3.loadCircleImage(movie.posterPath)
            binding.textView3.text = movie.releaseDate
            binding.textView5.text = movie.status
            binding.textView6.text = movie.voteAverage.toString()

        }

    }

    /* private fun observeGenre() {
         viewModel.genreList.observe(viewLifecycleOwner) { list ->
             if (list.isNullOrEmpty()) {
             } else {
                 genreListAdapter = DetailsAdapter(list)
                 binding.recyclerView3.adapter = genreListAdapter
             }

         }
     } */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}



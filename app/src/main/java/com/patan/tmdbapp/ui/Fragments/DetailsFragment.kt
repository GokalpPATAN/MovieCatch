package com.patan.tmdbapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.patan.tmdbapp.databinding.FragmentDetailsBinding
import com.patan.tmdbapp.ui.adapter.DetailsAdapter
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root


    }

    private fun observeEvents() {

        viewModel.detailList.observe(viewLifecycleOwner) { movie ->
            binding.textView.text = movie?.title
            binding.textView2.text = movie?.overview
            binding.textView3.text = movie?.releaseDate
            binding.textView5.text = movie?.status
            binding.textView6.text = movie?.voteAverage.toString()
            binding.imageView3.loadCircleImage(movie?.posterPath)
            val list1 = movie?.genres
            if (list1.isNullOrEmpty()) {
            } else {
                genreListAdapter = DetailsAdapter(list1)
                println(args.movieId)
                binding.recyclerView3.adapter = genreListAdapter
            }
            binding.progressBar.isVisible = false
            binding.imageView.isVisible = true
            binding.textView4.isVisible = true
            binding.textView7.isVisible = true
            binding.textView8.isVisible = true

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetails(movieId = args.movieId)
        observeEvents()
        binding.button.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}



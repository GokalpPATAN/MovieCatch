package com.patan.tmdbapp.ui.detail

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.patan.tmdbapp.databinding.FragmentDetailsBinding
import com.patan.tmdbapp.network.FirebaseClientImpl
import com.patan.tmdbapp.ui.adapter.CommentAdapter
import com.patan.tmdbapp.ui.adapter.DetailsAdapter
import com.patan.tmdbapp.util.loadCircleImage

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var genreListAdapter: DetailsAdapter
    private lateinit var commentAdapter: CommentAdapter
    private val args by navArgs<DetailsFragmentArgs>()
    private var auth: FirebaseAuth=FirebaseAuth.getInstance()
    private val userEmail = auth.currentUser?.email.toString()

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
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetails(movieId = args.movieId)
        viewModel.checkFavourite(movieId = args.movieId, userEmail)
        observeEvents()


        binding.FavCheck.setOnClickListener {

            if (binding.FavCheck.isChecked) {
                viewModel.addFavourite(movieId = args.movieId, userEmail)
            } else {
                viewModel.deleteFavourite(movieId = args.movieId, userEmail)
            }


        }
        binding.button.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToHomeFragment()
            findNavController().navigate(action)
        }


        binding.button2.setOnClickListener {
            if (!binding.CardView.isVisible) {
                binding.CardView.isVisible = true
            } else {
                binding.CardView.isVisible = false
                val comment = binding.commentEditText.text.toString()
                viewModel.detailList.observe(viewLifecycleOwner) { movie ->
                    if (it != null) {
                        val movieName = movie?.title.toString()
                        viewModel.addComment(movieName, userEmail, comment = comment)
                        viewModel.getCommentsFromDatabase(
                            movieName = movie?.title.toString()
                        )
                        viewModel.userName.observe(viewLifecycleOwner) {
                            viewModel.commentList.observe(viewLifecycleOwner) { comments ->
                                if (comments.isNullOrEmpty()) {
                                } else {
                                    commentAdapter = CommentAdapter(it, comments)
                                    binding.RecyclerView8.adapter = commentAdapter
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            if (!list1.isNullOrEmpty()) {
                genreListAdapter = DetailsAdapter(list1)
                binding.recyclerView3.adapter = genreListAdapter
            }
            binding.progressBar.isVisible = false
            binding.imageView.isVisible = true
            binding.textView4.isVisible = true
            binding.textView7.isVisible = true
            binding.textView8.isVisible = true
            binding.FavCheck.isVisible = true
            binding.button2.isVisible = true
            viewModel.isFavourite.observe(viewLifecycleOwner) { isFavourite ->
                binding.FavCheck.isChecked = isFavourite

                viewModel.getCommentsFromDatabase(
                    movieName = movie?.title.toString()
                )
                viewModel.userName.observe(viewLifecycleOwner) {
                    viewModel.commentList.observe(viewLifecycleOwner) { comments ->
                        if (comments.isNullOrEmpty()) {
                        } else {
                            commentAdapter = CommentAdapter(it, comments)
                            binding.RecyclerView8.adapter = commentAdapter
                        }
                    }
                }
            }

        }

    }
}
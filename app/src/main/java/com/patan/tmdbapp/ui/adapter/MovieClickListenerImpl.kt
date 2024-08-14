//package com.patan.tmdbapp.ui.adapter
//
//import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
//import com.patan.tmdbapp.ui.Fragments.HomeFragmentDirections
//import javax.inject.Inject
//
//class MovieClickListenerImpl @Inject constructor():MovieClickListener {
//    override fun onMovieClicked(movieId: Int?) {
//        if (movieId != null) {
//            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
//                movieId
//            )
//            findNavController().navigate(action)
//        }
//    }
//}
//package com.patan.tmdbapp.di
//
//import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
//import com.patan.tmdbapp.ui.Fragments.HomeFragment
//import com.patan.tmdbapp.ui.Fragments.HomeFragmentDirections
//import com.patan.tmdbapp.ui.adapter.MainAdapter
//import com.patan.tmdbapp.ui.adapter.MovieClickListener
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.FragmentComponent
//
//@Module
//@InstallIn(FragmentComponent::class)
//object AdapterModule {
//
//    @Provides
//    fun provideMovieClickListener(fragment: HomeFragment): MovieClickListener {
//        return object : MovieClickListener {
//            override fun onMovieClicked(movieId: Int?) {
//                if (movieId != null) {
//                    val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId)
//                    findNavController(fragment).navigate(action)
//                }
//            }
//        }
//    }
//
//    @Provides
//    fun provideMainAdapter(
//        movieClickListener: MovieClickListener
//    ): MainAdapter {
//        return MainAdapter(emptyList(), movieClickListener)
//    }
//}

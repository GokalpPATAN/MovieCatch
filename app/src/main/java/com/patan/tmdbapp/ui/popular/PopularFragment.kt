package com.patan.tmdbapp.ui.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.patan.tmdbapp.R
import com.patan.tmdbapp.databinding.FragmentPopularBinding


class PopularFragment : Fragment() {
    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }
       override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
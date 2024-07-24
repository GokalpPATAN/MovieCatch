package com.patan.tmdbapp.ui.top_rated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patan.tmdbapp.R
import com.patan.tmdbapp.databinding.FragmentRatedBinding


class RatedFragment : Fragment()  {
    private var _binding: FragmentRatedBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rated, container, false)
    }

}
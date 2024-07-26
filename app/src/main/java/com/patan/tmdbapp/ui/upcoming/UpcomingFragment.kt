package com.patan.tmdbapp.ui.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.patan.tmdbapp.databinding.FragmentUpcomingBinding

class UpcomingFragment : Fragment() {
    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UpcomingViewModel>()
    private lateinit var upcomingAdapter: UpcomingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentUpcomingBinding.inflate(inflater,container,false)

        viewModel.getUp()
        observeEvents()

        return binding.root
    }

    private fun observeEvents() {
    viewModel.upList.observe(viewLifecycleOwner){list ->
        if (list.isNullOrEmpty()){

        }else{
            upcomingAdapter=UpcomingAdapter(list)
            binding.recyclerView.adapter=upcomingAdapter
        }
    }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
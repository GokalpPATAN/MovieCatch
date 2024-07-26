package com.patan.tmdbapp.ui.top_rated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.patan.tmdbapp.databinding.FragmentRatedBinding


class RatedFragment : Fragment()  {
    private var _binding: FragmentRatedBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<RatedViewModel>()
    private lateinit var topListAdapter:RatedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentRatedBinding.inflate(inflater,container,false)

        viewModel.getTop()
        observeEvents()

        return binding.root

    }

    private fun observeEvents() {
        viewModel.ratedList.observe(viewLifecycleOwner){ list ->
            if(list.isNullOrEmpty()){

            }else{
                topListAdapter= RatedAdapter(list)
                binding.recyclerView4.adapter=topListAdapter
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
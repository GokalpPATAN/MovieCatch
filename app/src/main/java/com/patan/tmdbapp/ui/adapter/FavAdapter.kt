package com.patan.tmdbapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.patan.tmdbapp.databinding.FragmentDetailsBinding
import com.patan.tmdbapp.databinding.ItemDetailsRecyclerViewBinding
import com.patan.tmdbapp.databinding.ItemHomeRecyclerViewBinding
import com.patan.tmdbapp.model.Genre
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.util.loadCircleImage


class FavAdapter(private val favList: List<String?>) :
    RecyclerView.Adapter<FavAdapter.ViewHolder>() {
    class ViewHolder(
        val binding: ItemDetailsRecyclerViewBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDetailsRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}
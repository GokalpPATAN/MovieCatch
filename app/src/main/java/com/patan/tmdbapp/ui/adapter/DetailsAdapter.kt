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


class DetailsAdapter(private val genreList: List<Genre?>) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
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
        return genreList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genreList[position]

        holder.binding.Genre.text = genre?.name

    }
}
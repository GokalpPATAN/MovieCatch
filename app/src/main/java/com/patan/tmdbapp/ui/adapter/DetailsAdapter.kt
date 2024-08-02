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


class DetailsAdapter(private val detailList: List<Item?>,private val genreList: List<Genre?>) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    class ViewHolder(
        val binding: ItemDetailsRecyclerViewBinding,
        val binding2: FragmentDetailsBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDetailsRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), FragmentDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genreList[position]
        val detail = detailList[position]

        holder.binding.Genre.text = genre?.name
        holder.binding2.textView.text = detail?.title
        holder.binding2.textView2.text = detail?.overview
        holder.binding2.imageView3.loadCircleImage(detail?.posterPath)
        holder.binding2.textView3.text = detail?.releaseDate
        holder.binding2.textView5.text = detail?.status
        holder.binding2.textView6.text = detail?.voteAverage.toString()
        holder.binding2.textView4.isVisible = true
        holder.binding2.textView7.isVisible = true
        holder.binding2.imageView.isVisible = true
        holder.binding2.progressBar.isVisible = false

    }
}
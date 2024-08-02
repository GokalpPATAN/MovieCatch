package com.patan.tmdbapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patan.tmdbapp.databinding.ItemHomeRecyclerViewBinding
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.util.loadCircleImage


class SearchAdapter(
    private val searchList: List<Item?>,
    private val movieClickListener: MovieClickListener
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemHomeRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val search = searchList[position]

        holder.binding.MovieTitle.text = search?.title
        holder.binding.MovieDesc.text = search?.overview
        holder.binding.MovieImage.loadCircleImage(search?.posterPath)
        holder.binding.Rate.text = search?.voteAverage.toString()

        holder.binding.root.setOnClickListener {
            movieClickListener.onMovieClicked(movieId = search?.id)
        }
    }
}
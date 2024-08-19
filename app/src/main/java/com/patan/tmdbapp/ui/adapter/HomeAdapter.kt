package com.patan.tmdbapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.patan.tmdbapp.databinding.ItemHomeRecyclerViewBinding
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.util.Constants
import com.patan.tmdbapp.util.loadCircleImage

class HomeAdapter(
    private val movieClickListener: MovieClickListener
) : PagingDataAdapter<Item, HomeAdapter.ViewHolder>(Constants.DiffCallback) {

    class ViewHolder(val binding: ItemHomeRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val now = getItem(position)
        holder.binding.MovieTitle.text = now?.title ?: now?.originalName
        holder.binding.MovieDesc.text = now?.overview ?: "There is no overview for this movie."
        holder.binding.Rate.text = now?.voteAverage.toString()
        holder.binding.MovieImage.loadCircleImage(now?.posterPath)

        holder.binding.root.setOnClickListener {
            movieClickListener.onMovieClicked(movieId = now?.id)
        }
    }
}

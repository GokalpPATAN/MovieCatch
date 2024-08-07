package com.patan.tmdbapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patan.tmdbapp.databinding.FragmentHomeBinding
import com.patan.tmdbapp.databinding.ItemHomeRecyclerViewBinding
import com.patan.tmdbapp.model.Item
import com.patan.tmdbapp.util.loadCircleImage


class MainAdapter(
    private val list: List<Item?>,
    private val movieClickListener: MovieClickListener
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemHomeRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false),

            )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val now = list[position]
        holder.binding.MovieTitle.text = now?.title
        holder.binding.MovieDesc.text = now?.overview
        holder.binding.Rate.text = now?.voteAverage.toString()

        holder.binding.MovieImage.loadCircleImage(now?.posterPath)

        holder.binding.root.setOnClickListener(){
            Log.d("MainAdapter", "Movie clicked: ${now?.id}")
            movieClickListener.onMovieClicked(movieId = now?.id)
        }
    }
}
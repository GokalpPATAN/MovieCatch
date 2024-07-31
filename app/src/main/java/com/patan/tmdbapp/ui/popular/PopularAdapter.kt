package com.patan.tmdbapp.ui.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patan.tmdbapp.databinding.ItemHomeRecyclerViewBinding
import com.patan.tmdbapp.model.PopularItem
import com.patan.tmdbapp.util.loadCircleImage

interface MovieClickListener {
    fun onMovieClicked(movieId: Int?)
}

class PopularAdapter(
    private val popularList: List<PopularItem?>,
    private val movieClickListener: MovieClickListener
) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemHomeRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root)

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
        return popularList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val popular = popularList[position]

        holder.binding.MovieTitle.text = popular?.title
        holder.binding.MovieDesc.text = popular?.overview
        holder.binding.Rate.text = popular?.voteAverage.toString()

        holder.binding.MovieImage.loadCircleImage(popular?.posterPath)

        holder.binding.root.setOnClickListener {
            movieClickListener.onMovieClicked(movieId = popular?.id)
        }
    }

}
package com.patan.tmdbapp.ui.top_rated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patan.tmdbapp.databinding.ItemHomeRecyclerViewBinding
import com.patan.tmdbapp.model.RatedItem
import com.patan.tmdbapp.ui.popular.MovieClickListener
import com.patan.tmdbapp.util.loadCircleImage

interface MovieClickListener {
    fun onMovieClicked(movieId: Int?)
}

class RatedAdapter(private val ratedList: List<RatedItem?>,private val movieClickListener: MovieClickListener): RecyclerView.Adapter<RatedAdapter.ViewHolder>(){
    class ViewHolder(val binding: ItemHomeRecyclerViewBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return ratedList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rated=ratedList[position]

        holder.binding.MovieTitle.text=rated?.title
        holder.binding.MovieDesc.text=rated?.overview
        holder.binding.Rate.text=rated?.voteAverage.toString()
        holder.binding.MovieImage.loadCircleImage(rated?.posterPath)

        holder.binding.root.setOnClickListener {
            movieClickListener.onMovieClicked(movieId = rated?.id)
        }
    }

}
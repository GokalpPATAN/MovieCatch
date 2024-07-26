package com.patan.tmdbapp.ui.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patan.tmdbapp.databinding.ItemHomeRecyclerViewBinding
import com.patan.tmdbapp.model.UpcomingItem
import com.patan.tmdbapp.util.loadCircleImage

class UpcomingAdapter(private val upList: List<UpcomingItem?>):RecyclerView.Adapter<UpcomingAdapter.ViewHolder>() {
    class ViewHolder( val binding: ItemHomeRecyclerViewBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return upList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val up=upList[position]

        holder.binding.MovieTitle.text=up?.title
        holder.binding.MovieDesc.text=up?.overview
        holder.binding.Rate.text=up?.voteAverage.toString()
        holder.binding.MovieImage.loadCircleImage(up?.posterPath)
    }
}
package com.patan.tmdbapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patan.tmdbapp.databinding.ItemHomeRecyclerViewBinding
import com.patan.tmdbapp.model.NowPlayingItem
import com.patan.tmdbapp.util.loadCircleImage

class HomeAdapter(private val nowList: List<NowPlayingItem?>): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemHomeRecyclerViewBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return nowList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val now=nowList[position]

        holder.binding.MovieTitle.text=now?.title
        holder.binding.MovieDesc.text=now?.overview
        holder.binding.Rate.text=now?.voteAverage.toString()

        holder.binding.MovieImage.loadCircleImage(now?.posterPath)
    }
}
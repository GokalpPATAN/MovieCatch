package com.patan.tmdbapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patan.tmdbapp.databinding.ItemCommentRecyclerViewBinding


class CommentAdapter(private val commentList: List<String>,private val user: List<String>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    class ViewHolder(
        val binding: ItemCommentRecyclerViewBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommentRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = commentList[position]
        val username=user[position]

        holder.binding.textView9.text = "$username\n"+"$comment"

    }
}
package com.patan.tmdbapp.util

import androidx.recyclerview.widget.DiffUtil
import com.patan.tmdbapp.model.Item

class Constants {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val BEARER_KEY =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjZjlmYTE2N2ZmMTIwYzY2NzI2YThhMzE5YmZmNzFmMSIsIm5iZiI6MTcyMTgxMTI4Ny4yMjk3MzUsInN1YiI6IjY2OWU1N2M1ZGY5NGY4MDBjNDQ3YTZlZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QtkRlBrSrUUFCJPgkoIytPaurEhfi3NY0XPhFn9pnI4"
        const val STARTING_PAGE_INDEX = 1
    }
    object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}
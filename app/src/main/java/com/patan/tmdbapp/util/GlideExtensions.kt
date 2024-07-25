package com.patan.tmdbapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerCropTransform
import com.patan.tmdbapp.R

fun ImageView.loadCircleImage(path:String?) {
    Glide.with(this.context).load(Constants.IMAGE_URL + path).apply(centerCropTransform().error(R.drawable.baseline_error_24).circleCrop()).into(this)
}
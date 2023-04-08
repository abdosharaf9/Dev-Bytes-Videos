package com.abdosharaf.devbytesvideos.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.devbytesvideos.R
import com.abdosharaf.devbytesvideos.domain.DomainVideo
import com.abdosharaf.devbytesvideos.ui.VideosAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun ImageView.bindImage(url: String) {
    Glide.with(this.context).load(url)
        .apply(
            RequestOptions()
                .error(R.drawable.broken_image)
                .placeholder(R.drawable.loading_animation)
        ).into(this)
}

@BindingAdapter("goneIfNotNull")
fun View.goneIfNotNull(it: Any?) {
    this.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("dataList")
fun RecyclerView.setDataList(list: List<DomainVideo>?) {
    (this.adapter as VideosAdapter).submitList(list)
}
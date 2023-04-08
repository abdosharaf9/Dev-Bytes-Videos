package com.abdosharaf.devbytesvideos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.devbytesvideos.databinding.ItemVideoBinding
import com.abdosharaf.devbytesvideos.domain.DomainVideo

class VideosAdapter : ListAdapter<DomainVideo, VideoViewHolder>(VideosDiffUtil) {

    var onItemClicked: ((DomainVideo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }
}

class VideoViewHolder private constructor(private val binding: ItemVideoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DomainVideo, clickListener: ((DomainVideo) -> Unit)?) {
        binding.video = item
        binding.root.setOnClickListener {
            clickListener?.invoke(item)
        }
    }

    companion object {
        fun from(parent: ViewGroup): VideoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return VideoViewHolder(
                ItemVideoBinding.inflate(layoutInflater, parent, false)
            )
        }
    }
}

object VideosDiffUtil : DiffUtil.ItemCallback<DomainVideo>() {
    override fun areItemsTheSame(oldItem: DomainVideo, newItem: DomainVideo): Boolean {
        return newItem.url == oldItem.url
    }

    override fun areContentsTheSame(oldItem: DomainVideo, newItem: DomainVideo): Boolean {
        return newItem == oldItem
    }
}
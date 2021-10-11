package me.admund.uutask.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import me.admund.uutask.databinding.LayoutImageBinding
import me.admund.uutask.model.ImageModel

class ImagesAdapter : ListAdapter<ImageModel, ImageViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutImageBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            imageImageView.load(item.url)
            likesTextView.text = item.likes.toString()
        }
    }
}

class ImageViewHolder(val binding: LayoutImageBinding) : RecyclerView.ViewHolder(binding.root)

private class DiffCallback : DiffUtil.ItemCallback<ImageModel>() {

    override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel) =
        oldItem == newItem
}

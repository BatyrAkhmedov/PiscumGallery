package com.example.gallery.presentation.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gallery.R
import com.example.gallery.data.model.ImageItem
import com.example.gallery.presentation.listener.ImageLoadingListener

class ImageAdapter(
    private val onClick: (ImageItem) -> Unit
) : RecyclerView.Adapter<ImageViewHolder>() {
    private val images = mutableListOf<ImageItem>()

    fun updateData(newImages: List<ImageItem>) {
        images.clear()
        images.addAll(newImages)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.progressBar.visibility = View.VISIBLE
        val item = images[position]

        val displayMetrics = holder.itemView.context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val columnWidth = (screenWidth / 2.1).toInt()
        val aspectRatio = item.height.toFloat() / item.width
        val targetHeight = (columnWidth * aspectRatio).toInt()
        // рассчитали высоту для staggered
        holder.imageView.layoutParams.height = targetHeight
        holder.imageView.requestLayout()


        Glide.with(holder.itemView)
            .load(item.imageUrl)
            .listener(ImageLoadingListener(holder.progressBar))
            .into(holder.imageView)
        holder.imageView.setOnClickListener {
            onClick(item)
        }
    }
}
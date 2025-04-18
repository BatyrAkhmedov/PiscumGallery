package com.example.gallery.presentation.recyclerView

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.R

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
}
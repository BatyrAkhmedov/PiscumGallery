    package com.example.gallery.presentation

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.gallery.GalleryApp
import com.example.gallery.R
import com.example.gallery.presentation.recyclerView.ImageAdapter
import com.example.gallery.databinding.ActivityMainBinding
import com.example.gallery.di.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject

    class MainActivity : AppCompatActivity() {
        private lateinit var vb: ActivityMainBinding

        @Inject
        lateinit var viewModelFactory: ViewModelFactory

        private val viewModel: ImageViewModel by viewModels { viewModelFactory }

        override fun onCreate(savedInstanceState: Bundle?) {
            (application as GalleryApp).appComponent.inject(this)
            super.onCreate(savedInstanceState)
            vb = ActivityMainBinding.inflate(layoutInflater)
            setContentView(vb.root)
            val adapter = ImageAdapter { image ->
                showImageDialog(image.imageUrl)
            }
            vb.recyclerView.adapter = adapter
            viewModel.loadImages()

            vb.swipeRefresh.setOnRefreshListener { viewModel.loadImages() }
            viewModel.images.observe(this) { images ->
                adapter.updateData(images)
            }
            viewModel.isLoading.observe(this) { isLoading ->
                vb.swipeRefresh.isRefreshing = isLoading
            }
        }

        private fun showImageDialog(imageUrl: String) {
            val dialog = Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            dialog.setContentView(R.layout.dialog_image)
            val imageView: ImageView = dialog.findViewById(R.id.dialogImage)
            val progressBar: ProgressBar = dialog.findViewById(R.id.dialogProgressBar)
            progressBar.visibility = View.VISIBLE
            Picasso.get()
                .load(imageUrl)
                .into(imageView, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }
                    override fun onError(e: Exception?) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, "Ошибка загрузки изображения", Toast.LENGTH_SHORT).show()
                    }
                })
            dialog.show()
        }
    }
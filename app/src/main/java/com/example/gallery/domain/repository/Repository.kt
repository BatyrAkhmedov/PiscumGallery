package com.example.gallery.domain.repository

import com.example.gallery.data.model.ImageItem
import io.reactivex.Single

interface Repository {
    fun getImages(page: Int): Single<List<ImageItem>>
}
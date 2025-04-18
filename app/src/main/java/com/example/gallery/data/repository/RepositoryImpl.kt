package com.example.gallery.data.repository

import com.example.gallery.data.mapper.ImageItemMapper
import com.example.gallery.data.model.ImageItem
import com.example.gallery.domain.model.ImageItemDto
import com.example.gallery.data.retrofit.PicsumApiService
import com.example.gallery.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: PicsumApiService,
    private val mapper: ImageItemMapper,
) : Repository {
    override fun getImages(page: Int): Single<List<ImageItem>> {
        val list = apiService.getImages(page)
        return list.map { mapper.transformAll(it) }
    }
}
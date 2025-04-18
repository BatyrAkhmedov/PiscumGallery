package com.example.gallery.data.mapper

import com.example.gallery.data.model.ImageItem
import com.example.gallery.domain.model.ImageItemDto

class ImageItemMapper {
    fun transform(imageItemDto: ImageItemDto): ImageItem =
        ImageItem(
            imageUrl = imageItemDto.download_url,
            height = imageItemDto.height,
            width = imageItemDto.width,
        )

    fun transformAll(list: List<ImageItemDto>): List<ImageItem> {
        return list.map (::transform)
    }
}
package com.example.gallery.domain.model

data class ImageItemDto(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url: String
)
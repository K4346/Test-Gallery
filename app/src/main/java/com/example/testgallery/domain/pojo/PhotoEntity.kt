package com.example.testgallery.domain.pojo


data class PhotoEntity(
    val id: Int,
    val name: String,
    val description: String,
    val image: ImageInfo
)
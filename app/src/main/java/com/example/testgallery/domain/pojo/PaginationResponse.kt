package com.example.testgallery.domain.pojo


data class PaginationResponse<T>(
    val countOfPages: Int,
    val data: List<T>
)
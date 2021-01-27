package com.example.testgallery.domain.repositories

import com.example.testgallery.domain.pojo.PaginationResponse
import com.example.testgallery.domain.pojo.PhotoEntity
import io.reactivex.Single

interface GalleryModel {

    fun getSingleObject(
        new: String = "false",
        popularity: String = "false",
        page: Int = 1
    ): Single<PaginationResponse<PhotoEntity>>
}
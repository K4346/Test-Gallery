package com.example.testgallery.domain.repositories

import com.example.testgallery.domain.pojo.PaginationResponse
import com.example.testgallery.domain.pojo.PhotoEntity
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface GalleryModel {

    fun getSingleObject(
        new: String = "false",
        popularity: String = "false",
        page: Int = 1
    ): Deferred<Response<PaginationResponse<PhotoEntity>>>
}
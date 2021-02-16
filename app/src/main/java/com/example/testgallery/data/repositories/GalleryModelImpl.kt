package com.example.testgallery.data.repositories

import android.util.Log
import com.example.testgallery.data.api.ApiFactory
import com.example.testgallery.domain.pojo.PaginationResponse
import com.example.testgallery.domain.pojo.PhotoEntity
import com.example.testgallery.domain.repositories.GalleryModel
import io.reactivex.Single

class GalleryModelImpl : GalleryModel {

    override fun getSingleObject(
        new: String,
        popularity: String,
        page: Int
    ): Single<PaginationResponse<PhotoEntity>> {
        return ApiFactory.apiService.getPhotosInfo(new, popularity, page)
    }
}
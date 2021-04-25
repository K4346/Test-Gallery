package com.example.testgallery.data.repositories

import com.example.testgallery.di.App
import com.example.testgallery.domain.pojo.PaginationResponse
import com.example.testgallery.domain.pojo.PhotoEntity
import com.example.testgallery.domain.repositories.GalleryModel
import kotlinx.coroutines.Deferred
import retrofit2.Response

class GalleryModelImpl : GalleryModel {


    override fun getSingleObject(
        new: String,
        popularity: String,
        page: Int
    ): Deferred<Response<PaginationResponse<PhotoEntity>>> {
        val apiService = App.component.provideApi()

        return if (new == "true")
            apiService.getNewPhotosInfo(new, page)
        else
            apiService.getPopularPhotosInfo(popularity, page)
    }
}
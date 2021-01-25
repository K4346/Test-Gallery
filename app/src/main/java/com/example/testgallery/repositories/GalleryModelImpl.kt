package com.example.testgallery.repositories

import com.example.testgallery.api.ApiFactory
import com.example.testgallery.pojo.DataObject
import com.example.testgallery.pojo.Datum
import io.reactivex.Single

class GalleryModelImpl:GalleryModel {

    override fun getSingleObject(new: String, popularity: String, page: Int): Single<DataObject> {
        return ApiFactory.apiService.getPhotosInfo(new,popularity,page)
    }
}
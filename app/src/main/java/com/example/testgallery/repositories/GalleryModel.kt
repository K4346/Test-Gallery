package com.example.testgallery.repositories

import com.example.testgallery.pojo.DataObject
import com.example.testgallery.pojo.Datum
import io.reactivex.Single

interface GalleryModel {

    fun getSingleObject(new: String="false", popularity: String="false", page: Int=1): Single<DataObject>
}
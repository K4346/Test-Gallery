package com.example.testgallery.api

import com.example.testgallery.pojo.DataObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    fun getPhotosInfo(
        @Query("new") new: String,
        @Query("popular") popular: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Single<DataObject>
}
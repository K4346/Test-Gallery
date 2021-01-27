package com.example.testgallery.data.api

import com.example.testgallery.domain.pojo.PaginationResponse
import com.example.testgallery.domain.pojo.PhotoEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    fun getPhotosInfo(
        @Query("new") new: String,
        @Query("popular") popular: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10
    ): Single<PaginationResponse<PhotoEntity>>
}
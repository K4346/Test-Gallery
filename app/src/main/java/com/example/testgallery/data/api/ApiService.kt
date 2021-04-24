package com.example.testgallery.data.api

import com.example.testgallery.domain.pojo.PaginationResponse
import com.example.testgallery.domain.pojo.PhotoEntity
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    fun getNewPhotosInfo(
        @Query("new") new: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10
    ): Deferred<Response<PaginationResponse<PhotoEntity>>>


    @GET("photos")
    fun getPopularPhotosInfo(
        @Query("popular") popular: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10
    ):  Deferred<Response<PaginationResponse<PhotoEntity>>>
}
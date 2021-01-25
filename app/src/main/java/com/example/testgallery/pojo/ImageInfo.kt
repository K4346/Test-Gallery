package com.example.testgallery.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ImageInfo (
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String
)
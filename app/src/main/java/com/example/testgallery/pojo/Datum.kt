package com.example.testgallery.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Datum (

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose val name: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("image")
    @Expose
    val image: ImageInfo
)
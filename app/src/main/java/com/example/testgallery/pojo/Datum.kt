package com.example.testgallery.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Datum (

    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("name")
    @Expose val name: String? = null,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("image")
    @Expose
    val image: ImageInfo
)
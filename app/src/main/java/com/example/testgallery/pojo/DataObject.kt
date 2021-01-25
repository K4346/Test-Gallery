package com.example.testgallery.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class DataObject (
    @SerializedName("data")
    @Expose
    val data: List<Datum>
)
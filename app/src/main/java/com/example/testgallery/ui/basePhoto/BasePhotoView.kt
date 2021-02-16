package com.example.testgallery.ui.basePhoto

import com.example.testgallery.domain.pojo.PhotoEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BasePhotoView:MvpView  {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun loadPhotos(photoEntities: List<PhotoEntity>)


    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun connectionInternet(flag: Boolean)

}
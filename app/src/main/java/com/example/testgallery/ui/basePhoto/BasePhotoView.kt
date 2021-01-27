package com.example.testgallery.ui.basePhoto

import com.example.testgallery.domain.pojo.PhotoEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface BasePhotoView : MvpView {

    fun loadPhotos(photoEntities: List<PhotoEntity>)

    fun connectionInternet(flag: Boolean)

}
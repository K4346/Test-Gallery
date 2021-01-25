package com.example.testgallery

import com.example.testgallery.pojo.Datum
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface GeneraFragmentView : MvpView {

    fun loadPhotos(data: List<Datum>)
}
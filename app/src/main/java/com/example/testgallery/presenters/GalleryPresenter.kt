package com.example.testgallery.presenters

import com.example.testgallery.view.GalleryView
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class GalleryPresenter : MvpPresenter<GalleryView>(){
    private val compositeDisposable = CompositeDisposable()
    fun disposeComposite() {
        compositeDisposable.dispose()
    }

}
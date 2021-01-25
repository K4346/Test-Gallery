package com.example.testgallery

import android.util.Log
import com.example.testgallery.pojo.Datum
import com.example.testgallery.repositories.GalleryModel
import com.example.testgallery.repositories.GalleryModelImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class GalleryPresenter : MvpPresenter<GalleryView>(){
    private val compositeDisposable = CompositeDisposable()
    fun disposeComposite() {
        compositeDisposable.dispose()
    }

}
package com.example.testgallery.ui.basePhoto

import com.example.testgallery.data.repositories.GalleryModelImpl
import com.example.testgallery.domain.repositories.GalleryModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class BasePhotoPresenter : MvpPresenter<BasePhotoView>() {
    private val compositeDisposable = CompositeDisposable()
    val model: GalleryModel = GalleryModelImpl()
    var isLoading: Boolean = true
    var pagesOnRecycler = 1

    fun loadData(new: String = "false", popularity: String = "false", page: Int = 1) {
        val disposable =
            model.getSingleObject(new = new, popularity = popularity, page = page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.connectionInternet(true)
                    viewState.loadPhotos(it.data)
                }, {
                    viewState.connectionInternet(false)
                    pagesOnRecycler = 1
                })
        compositeDisposable.add(disposable)
    }

}
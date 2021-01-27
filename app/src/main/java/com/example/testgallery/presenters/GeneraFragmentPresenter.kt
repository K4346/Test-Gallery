package com.example.testgallery.presenters

import android.util.Log
import com.example.testgallery.view.GeneraFragmentView
import com.example.testgallery.pojo.Datum
import com.example.testgallery.repositories.GalleryModel
import com.example.testgallery.repositories.GalleryModelImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class GeneraFragmentPresenter : MvpPresenter<GeneraFragmentView>() {
    private val compositeDisposable = CompositeDisposable()
    val model: GalleryModel = GalleryModelImpl()
    var isLoading:Boolean=true
    var pagesOnRecycler = 1

    fun loadData(new: String = "false", popularity: String = "false", page: Int = 1) {
        val disposable =
            model.getSingleObject(new = new, popularity = popularity, page = page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("qwerty",pagesOnRecycler.toString())

                    viewState.ConnectionInternet(true)
                    viewState.loadPhotos(it.data)

                }, {
                    viewState.ConnectionInternet(false)
                    pagesOnRecycler=1
                })
        compositeDisposable.add(disposable)
    }


    fun disposeComposite() {
        compositeDisposable.dispose()
    }


}
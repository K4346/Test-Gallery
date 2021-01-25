package com.example.testgallery

import android.util.Log
import com.example.testgallery.api.ApiFactory
import com.example.testgallery.pojo.Datum
import com.example.testgallery.repositories.GalleryModel
import com.example.testgallery.repositories.GalleryModelImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class GalleryPresenter: MvpPresenter<GalleryView>() {
    var dataList = ArrayList<Datum>()
    private val compositeDisposable = CompositeDisposable()
    val model:GalleryModel=GalleryModelImpl()

    fun loadData(new: String="false", popularity: String="false", page: Int=1) {
        Log.i("qwertys",new+popularity)
        val disposable =
           model.getSingleObject(new = new, popularity = popularity, page = page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                       viewState.loadPhotos(it.data)

                }, {
                })
        compositeDisposable.add(disposable)
    }




fun disposeComposite(){
    compositeDisposable.dispose()
}

}
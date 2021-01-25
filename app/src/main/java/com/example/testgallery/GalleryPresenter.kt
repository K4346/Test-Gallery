package com.example.testgallery

import android.util.Log
import com.example.testgallery.api.ApiFactory
import com.example.testgallery.pojo.Datum
import com.example.testgallery.pojo.ImageInfo
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class GalleryPresenter: MvpPresenter<GalleryView>() {
    var dataList = ArrayList<Datum>()
    private val compositeDisposable = CompositeDisposable()


    fun loadData(new: String="false", popularity: String="false", page: Int=1) {
        val disposable =
            ApiFactory.apiService.getPhotosInfo(new = new, popular = popularity, page = page)
                .map {
                    it.data
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        viewState.loadPhotos(it)
                        Log.i("qwerty",  dataList.toString())

                    }
                }, {
                    Log.i("qwerty", "it.toString(")
                })
        compositeDisposable.add(disposable)
    }




fun disposeComposite(){
    compositeDisposable.dispose()
}

}
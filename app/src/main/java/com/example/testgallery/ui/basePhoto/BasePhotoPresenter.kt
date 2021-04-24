package com.example.testgallery.ui.basePhoto

import com.example.testgallery.data.repositories.GalleryModelImpl
import com.example.testgallery.domain.repositories.GalleryModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import moxy.InjectViewState
import moxy.MvpPresenter
import kotlin.coroutines.CoroutineContext

@InjectViewState
class BasePhotoPresenter : MvpPresenter<BasePhotoView>(), CoroutineScope {
    val model: GalleryModel = GalleryModelImpl()
    var isLoading: Boolean = true
    var pagesOnRecycler = 1

    fun loadData(new: String = "false", popularity: String = "false", page: Int = 1) {
        launch(IO) {
            val res = model.getSingleObject(new = new, popularity = popularity, page = page).await()
            launch(Main) {
                if (res.isSuccessful && res.body() != null) {
                    if (page == res.body()?.countOfPages) {
                        viewState.toastEndPages()
                    } else {
                        viewState.connectionInternet(true)
                        res.body()?.data?.let { viewState.loadPhotos(it) }
                    }
                } else {
                    viewState.connectionInternet(false)
                    pagesOnRecycler = 1
                }
            }
        }

//            model.getSingleObject(new = new, popularity = popularity, page = page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    if (page == it.countOfPages) {
//                        viewState.toastEndPages()
//                    } else {
//                        viewState.connectionInternet(true)
//                        viewState.loadPhotos(it.data)
//                    }
//                }, {
//                    viewState.connectionInternet(false)
//                    pagesOnRecycler = 1
//                })

    }

    protected val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    protected val Scope = CoroutineScope(coroutineContext)

    override fun onDestroy() {
        Scope.cancel()
    }
}

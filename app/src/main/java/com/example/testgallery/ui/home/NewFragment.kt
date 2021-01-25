package com.example.testgallery.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testgallery.GalleryActivity
import com.example.testgallery.GalleryPresenter
import com.example.testgallery.GalleryView
import com.example.testgallery.R
import com.example.testgallery.adapter.GalleryAdapter
import com.example.testgallery.api.ApiFactory
import com.example.testgallery.pojo.DataObject
import com.example.testgallery.pojo.Datum
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.recycler_view.*
import moxy.MvpAppCompatFragment
import java.lang.reflect.Array


class NewFragment : MvpAppCompatFragment(), GalleryView {

    private lateinit var presenter: GalleryPresenter
//   private lateinit var galleryActivity: GalleryActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = GalleryPresenter()
        presenter.loadData(new = "true")
        Log.i("qwerty", "qaz "+presenter.dataList.toString())
    }
    override fun loadPhotos(data: List<Datum>) {
//        val adapter=GalleryAdapter()
//        adapter.imageList=presenter.dataList
//        Log.i("qwerty", adapter.imageList.toString())
//        recyclerVIewOfImage.adapter = adapter
//        recyclerVIewOfImage.layoutManager = GridLayoutManager(context, 2)
        for (i in data) {
            presenter.dataList.add(i)
            Log.i("qwerty",i.image.name)
            Log.i("qwerty", "qaz "+presenter.dataList.toString())
        }
        Log.i("qwerty", "qaz "+presenter.dataList.toString())
    }

}
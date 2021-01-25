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
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.lang.reflect.Array


class NewFragment : GeneralFragment("true","false")

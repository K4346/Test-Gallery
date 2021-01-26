package com.example.testgallery.ui.Gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testgallery.*
import com.example.testgallery.adapter.GalleryAdapter
import com.example.testgallery.pojo.Datum
import com.example.testgallery.presenters.GeneraFragmentPresenter
import com.example.testgallery.view.GeneraFragmentView
import kotlinx.android.synthetic.main.recycler_view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


open class GeneralFragment(
    private val new: String,
    private val popular: String
) : MvpAppCompatFragment(), GeneraFragmentView {


    @InjectPresenter
    lateinit var presenter: GeneraFragmentPresenter

    private val adapter = GalleryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var mContext: FragmentActivity? = FragmentActivity()
        mContext = this!!.getActivity() as GalleryActivity
        mContext.changeActionBar(popular)
        presenter.loadData(new = new, popularity = popular)
        adapter.onPhotoClickListener = object : GalleryAdapter.OnPhotoClickListener {
            override fun onPhotoClick(datum: Datum) {
                val idtoDetail = Bundle()
                idtoDetail.putString("name", datum.name)
                idtoDetail.putString("description", datum.description)
                idtoDetail.putString("url", datum.image.name)
                findNavController().navigate(R.id.detail_navigation, idtoDetail)

            }
        }


    }

    override fun loadPhotos(data: List<Datum>) {
        adapter.imageList = data
        recyclerVIewOfImage.adapter = adapter
        recyclerVIewOfImage.layoutManager = GridLayoutManager(context, 2)
    }

    override fun ConnectionInternet(flag: Boolean) {
        var mContext: FragmentActivity? = FragmentActivity()
        mContext = this!!.getActivity() as GalleryActivity
        if (flag==false) mContext.badConnection()
        else mContext.goodConnection()
    }


}

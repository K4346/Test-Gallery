package com.example.testgallery.ui.Gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testgallery.*
import com.example.testgallery.adapter.GalleryAdapter
import com.example.testgallery.pojo.Datum
import com.example.testgallery.presenters.GeneraFragmentPresenter
import com.example.testgallery.view.GeneraFragmentView
import kotlinx.android.synthetic.main.recycler_view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import java.util.ArrayList


open class GeneralFragment(
    private val new: String,
    private val popular: String

) : MvpAppCompatFragment(), GeneraFragmentView, SwipeRefreshLayout.OnRefreshListener {


    @InjectPresenter
    lateinit var presenter: GeneraFragmentPresenter

    private val adapter = GalleryAdapter()
    private var spisokDatum = ArrayList<Datum>()
    private var hasNext = true
    private var pagesOnRecycler = 1
    private var isLoading = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swipe_container.setOnRefreshListener(this)

        var mContext: FragmentActivity? = FragmentActivity()
        mContext = this!!.getActivity() as GalleryActivity
        mContext.changeActionBar(popular)

        val myLayoutManager = GridLayoutManager(context, 2)
        recyclerVIewOfImage.layoutManager = myLayoutManager

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

        recyclerVIewOfImage.setOnScrollChangeListener { _: View, _: Int, _: Int, _: Int, _: Int ->
            val lastvisibleitemposition = myLayoutManager.findLastVisibleItemPosition()
            if (pagesOnRecycler == 2) {

                val k = lastvisibleitemposition
            }
            if (lastvisibleitemposition == adapter.itemCount - 1) {
                if (!isLoading && hasNext) {
                    isLoading = true
                    pagesOnRecycler += 1
                    presenter.loadData(new = new, popularity = popular, page = pagesOnRecycler)
                }
            }
        }


    }


    override fun loadPhotos(data: List<Datum>) {
//        val k=ArrayList<Datum>()
//        Log.i("qwerty",adapter.imageList.toString())
//        k.addAll(adapter.imageList)
//        k.addAll(data)
        spisokDatum.addAll(data)
        adapter.imageList = spisokDatum
        recyclerVIewOfImage.adapter = adapter
    }

    override fun ConnectionInternet(flag: Boolean) {
        val context = requireActivity() as GalleryActivity
        if (!flag) {
            context.badConnection()
        } else {
            context.goodConnection()
        }
    }

    fun loadMorePhotos(data: ArrayList<Datum>) {
        adapter.imageList = data
        recyclerVIewOfImage.adapter = adapter
    }

    override fun onRefresh() {
        adapter.imageList.clear()
        adapter.notifyDataSetChanged()
        recyclerVIewOfImage.adapter = adapter
        pagesOnRecycler = 1
        presenter.loadData(new, popular, pagesOnRecycler)
    }


}

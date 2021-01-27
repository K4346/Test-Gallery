package com.example.testgallery.ui.Gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


open class GeneralFragment(
    private val new: String,
    private val popular: String

) : MvpAppCompatFragment(), GeneraFragmentView, SwipeRefreshLayout.OnRefreshListener {


    @InjectPresenter
    lateinit var presenter: GeneraFragmentPresenter

    private val adapter = GalleryAdapter()

//    var isLoading=true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swipe_container.setOnRefreshListener(this)
        presenter.pagesOnRecycler=1
       val mContext = requireActivity() as GalleryActivity
        mContext.changeActionBar(popular)

        val myLayoutManager = GridLayoutManager(context, 2)
        recyclerVIewOfImage.layoutManager = myLayoutManager
        recyclerVIewOfImage.adapter = adapter
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
            Log.i("qwerty",lastvisibleitemposition.toString()+"  "+ (adapter.itemCount - 1).toString())
            if ((lastvisibleitemposition == adapter.itemCount - 1) &&(lastvisibleitemposition != -1) ) {
               if(presenter.isLoading){
                   presenter.isLoading=false
                presenter.pagesOnRecycler += 1
                    presenter.loadData(new = new, popularity = popular, page = presenter.pagesOnRecycler)}
            }
        }


    }


    override fun loadPhotos(data: List<Datum>) {
        val position: Int = adapter.imageList.size
        adapter.imageList.addAll(data)
        adapter.notifyItemRangeInserted(position, data.size)
       presenter.isLoading=true
    }

    override fun ConnectionInternet(flag: Boolean) {
        val context = requireActivity() as GalleryActivity
        if (!flag) {
            context.badConnection()
        } else {
            context.goodConnection()
        }
    }


    override fun onRefresh() {
        adapter.imageList.clear()
        adapter.notifyDataSetChanged()
        presenter.pagesOnRecycler = 1
//        Log.i("qwerty",presenter.pagesOnRecycler.toString())
        presenter.loadData(new, popular, presenter.pagesOnRecycler)
        swipe_container.isRefreshing=false
    }


}

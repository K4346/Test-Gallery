package com.example.testgallery.ui.basePhoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testgallery.R
import com.example.testgallery.domain.pojo.PhotoEntity
import com.example.testgallery.ui.basePhoto.adapters.GalleryAdapter
import com.example.testgallery.ui.main.MainActivity
import kotlinx.android.synthetic.main.recycler_view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

open class BasePhotoFragment(
    private val new: String,
    private val popular: String
) : MvpAppCompatFragment(), BasePhotoView, SwipeRefreshLayout.OnRefreshListener {

    @InjectPresenter
    lateinit var presenter: BasePhotoPresenter

    private val adapter = GalleryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swipe_container.setOnRefreshListener(this)

        presenter.pagesOnRecycler = 1
        val mContext = requireActivity() as MainActivity
        mContext.changeActionBar(popular)
        mContext.textToButtonOnActionBar(false)
        val myLayoutManager = GridLayoutManager(context, 2)
        recyclerVIewOfImage.layoutManager = myLayoutManager
        recyclerVIewOfImage.adapter = adapter
        presenter.loadData(new = new, popularity = popular)

        adapter.onPhotoClickListener = object : GalleryAdapter.OnPhotoClickListener {
            override fun onPhotoClick(photoEntity: PhotoEntity) {
                val idtoDetail = Bundle()
                idtoDetail.putString("name", photoEntity.name)
                idtoDetail.putString("description", photoEntity.description)
                idtoDetail.putString("url", photoEntity.image.name)
                findNavController().navigate(R.id.detail_navigation, idtoDetail)
            }
        }

        recyclerVIewOfImage.setOnScrollChangeListener { _: View, _: Int, _: Int, _: Int, _: Int ->
            val lastVisibleItemPosition = myLayoutManager.findLastVisibleItemPosition()
            if ((lastVisibleItemPosition == adapter.itemCount - 1) && (lastVisibleItemPosition != -1)) {
                if (presenter.isLoading) {
                    presenter.isLoading = false
                    pb_for_rv.visibility = View.VISIBLE
                    presenter.pagesOnRecycler += 1
                    presenter.loadData(
                        new = new,
                        popularity = popular,
                        page = presenter.pagesOnRecycler
                    )
                }
            }
        }

    }


    override fun loadPhotos(photoEntities: List<PhotoEntity>) {
        val position: Int = adapter.imageList.size
        adapter.imageList.addAll(photoEntities)
        adapter.notifyItemRangeInserted(position, photoEntities.size)
        pb_for_rv.visibility = View.INVISIBLE
        presenter.isLoading = true
    }

    override fun connectionInternet(flag: Boolean) {
        if (!flag) {
            badConnection()
        } else {
            goodConnection()
        }
    }

    override fun onRefresh() {
        adapter.imageList.clear()
        adapter.notifyDataSetChanged()
        presenter.pagesOnRecycler = 1
        presenter.loadData(new, popular, presenter.pagesOnRecycler)
        swipe_container.isRefreshing = false


    }


    private fun goodConnection() {

        if (llBadConnection.visibility == View.VISIBLE) {
            llBadConnection.visibility = View.INVISIBLE
        }

    }

    private fun badConnection() {
        if (llBadConnection.visibility == View.INVISIBLE) {
            llBadConnection.visibility = View.VISIBLE

            if (!presenter.isLoading) {
                pb_for_rv.visibility = View.INVISIBLE
                presenter.isLoading = true
            }
        }
    }

    override fun toastEndPages() {
        Toast.makeText(requireContext(), "Конец списка", Toast.LENGTH_SHORT).show()
        pb_for_rv.visibility = View.INVISIBLE
    }

}

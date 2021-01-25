package com.example.testgallery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testgallery.R
import com.example.testgallery.api.ApiFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_activity.*
import moxy.MvpAppCompatFragment

class DetailFragment : MvpAppCompatFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.detail_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        var mContext : FragmentActivity?  = FragmentActivity()
//        mContext = this!!.getActivity() as GalleryActivity
//        mContext.textToButtonOnActionBar()


        val nameofImage = requireArguments().getString("name")
        val descriptionOfImage = requireArguments().getString("description")
        val urlOfImage = ApiFactory.BASE_URL_IMAGE + requireArguments().getString("url")
        Picasso.get().load(urlOfImage).into(ivTitleImage)
        tvTitle.text = nameofImage
        tvDescribtion.text = descriptionOfImage

    }


}
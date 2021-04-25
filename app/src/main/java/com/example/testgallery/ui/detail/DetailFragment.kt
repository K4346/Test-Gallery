package com.example.testgallery.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testgallery.R
import com.example.testgallery.di.RetrofitModule
import com.example.testgallery.ui.main.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.actionbars.*
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

        val nameofImage = requireArguments().getString("name")
        val descriptionOfImage = requireArguments().getString("description")
        val urlOfImage = RetrofitModule.BASE_URL_IMAGE + requireArguments().getString("url")
        Picasso.get().load(urlOfImage).fit().into(ivTitleImage)
        tvTitle.text = nameofImage
        tvDescribtion.text = descriptionOfImage

        val mContext = requireActivity() as MainActivity
        mContext.textToButtonOnActionBar(true)
        mContext.arrow_button.setOnClickListener {
            mContext.onBackPressed()
            mContext.textToButtonOnActionBar(false)
        }
    }

}
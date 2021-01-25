package com.example.testgallery.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testgallery.R
import com.example.testgallery.api.ApiFactory
import com.example.testgallery.pojo.Datum
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_item.view.*

class GalleryAdapter() : RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {
    var imageList: List<Datum> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onPhotoClickListener: OnPhotoClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        Log.i("qwerty", "ApiFactory.BASE_URL_IMAGE+image.image.name")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return GalleryHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        val image = imageList[position]
        Picasso.get().load(ApiFactory.BASE_URL_IMAGE + image.image.name).into(holder.image)
        holder.itemView.setOnClickListener {
            onPhotoClickListener?.onPhotoClick(image)
        }
    }

    override fun getItemCount() = imageList.size


    inner class GalleryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.ivPhoto
    }

    interface OnPhotoClickListener {
        fun onPhotoClick(datum: Datum)
    }
}
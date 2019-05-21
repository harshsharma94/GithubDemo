package com.waynetech.githubdemo.data

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ImageHandlerImpl @Inject constructor(context: Context) : ImageHandler {

    private val picasso: Picasso = Picasso.Builder(context).build()

    override fun with(url: String, imageView: ImageView) {
        picasso.load(url).fit().into(imageView)
    }

}

interface ImageHandler {
    fun with(url: String, imageView: ImageView)
}
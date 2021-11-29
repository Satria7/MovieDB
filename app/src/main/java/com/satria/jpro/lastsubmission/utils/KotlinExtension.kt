package com.satria.jpro.lastsubmission.utils
//copyright satria junanda//
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.satria.jpro.lastsubmission.R

fun ImageView.loadFromUrl(path: String) {
    Glide.with(this).clear(this)
    Glide.with(this)
        .setDefaultRequestOptions(RequestOptions()
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_loading)
    ).load(path).into(this)
}
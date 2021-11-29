package com.satria.jpro.lastsubmission.ui.home.content.movie
//copyright satria junanda//
import com.satria.jpro.lastsubmission.data.source.local.entity.MovieEntity

interface MovieCallback {
    fun onItemClicked(data: MovieEntity)
}
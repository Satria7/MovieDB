package com.satria.jpro.lastsubmission.ui.home.content.tvshow
//copyright satria junanda//
import com.satria.jpro.lastsubmission.data.source.local.entity.TvShowEntity

interface TvShowCallback {
    fun onItemClicked(data: TvShowEntity)
}
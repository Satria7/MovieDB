package com.satria.jpro.lastsubmission.ui.home
//copyright satria junanda//
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.satria.jpro.lastsubmission.data.source.CatalogRepository
import com.satria.jpro.lastsubmission.data.source.local.entity.MovieEntity
import com.satria.jpro.lastsubmission.data.source.local.entity.TvShowEntity
import com.satria.jpro.lastsubmission.vo.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getListNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>> = catalogRepository.getNowPlayingMovies()

    fun getListNowPlayingComingSoon(): LiveData<Resource<PagedList<MovieEntity>>> = catalogRepository.getNowPlayingComingSoon()

    fun getListOnTheAirTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = catalogRepository.getTvShowOnTheAir()

}
//copyright satria junanda//
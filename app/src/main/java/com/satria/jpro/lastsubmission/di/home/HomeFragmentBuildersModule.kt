package com.satria.jpro.lastsubmission.di.home
//copyright satria junanda//
import com.satria.jpro.lastsubmission.di.home.favorite.FavoriteFragmentBuildersModule
import com.satria.jpro.lastsubmission.ui.home.content.favorite.FavoriteFragment
import com.satria.jpro.lastsubmission.ui.home.content.movie.MovieFragment
import com.satria.jpro.lastsubmission.ui.home.content.tvshow.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment() : MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment() : TvShowFragment

    @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
    abstract fun contributeFavoriteFragment() : FavoriteFragment
}
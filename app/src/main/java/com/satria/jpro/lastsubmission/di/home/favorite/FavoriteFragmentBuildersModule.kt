package com.satria.jpro.lastsubmission.di.home.favorite
//copyright satria junanda//
import com.satria.jpro.lastsubmission.ui.home.content.favorite.movie.FavoriteMovieFragment
import com.satria.jpro.lastsubmission.ui.home.content.favorite.tvshow.FavoriteTvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFavoriteMovieFragment() : FavoriteMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteTvShowFragment() : FavoriteTvShowFragment
}
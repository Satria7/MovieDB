package com.satria.jpro.lastsubmission.di
//copyright satria junanda//
import com.satria.jpro.lastsubmission.di.home.HomeFragmentBuildersModule
import com.satria.jpro.lastsubmission.ui.detail.DetailActivity
import com.satria.jpro.lastsubmission.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

//copyright satria junanda//
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity

}
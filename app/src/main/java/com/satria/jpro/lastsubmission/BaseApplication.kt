package com.satria.jpro.lastsubmission
//copyright satria junanda//
import com.satria.jpro.lastsubmission.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
//copyright satria junanda//
class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

}
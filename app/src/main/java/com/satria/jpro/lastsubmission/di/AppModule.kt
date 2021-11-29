package com.satria.jpro.lastsubmission.di
//copyright satria junanda//
import android.app.Application
import com.satria.jpro.lastsubmission.data.source.CatalogRepository
import com.satria.jpro.lastsubmission.data.source.local.LocalDataSource
import com.satria.jpro.lastsubmission.data.source.local.room.CatalogDao
import com.satria.jpro.lastsubmission.data.source.local.room.CatalogDatabase
import com.satria.jpro.lastsubmission.data.source.remote.RemoteDataSource
import com.satria.jpro.lastsubmission.data.source.remote.api.ApiService
import com.satria.jpro.lastsubmission.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
//copyright satria junanda//

@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideCatalogDatabase(application: Application): CatalogDatabase =
            CatalogDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideCatalogDao(catalogDatabase: CatalogDatabase): CatalogDao =
            catalogDatabase.catalogDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(catalogDao: CatalogDao): LocalDataSource =
            LocalDataSource(catalogDao)

        @Singleton
        @Provides
        fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
            RemoteDataSource(apiService)

        @Singleton
        @Provides
        fun provideCatalogRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): CatalogRepository = CatalogRepository(remoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(catalogRepository: CatalogRepository): ViewModelFactory =
            ViewModelFactory(catalogRepository)

    }
}
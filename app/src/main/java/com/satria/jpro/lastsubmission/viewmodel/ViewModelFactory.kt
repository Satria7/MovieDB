package com.satria.jpro.lastsubmission.viewmodel
//copyright satria junanda//
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.satria.jpro.lastsubmission.data.source.CatalogRepository
import com.satria.jpro.lastsubmission.ui.detail.DetailViewModel
import com.satria.jpro.lastsubmission.ui.home.HomeViewModel
import com.satria.jpro.lastsubmission.ui.home.content.favorite.FavoriteViewModel
import javax.inject.Inject
//copyright satria junanda//
class ViewModelFactory @Inject constructor(private val mCatalogRepository: CatalogRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mCatalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}
//copyright satria junanda//
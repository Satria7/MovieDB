package com.satria.jpro.lastsubmission.data.source.remote
//copyright satria junanda//
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.satria.jpro.lastsubmission.data.source.remote.api.ApiService
import com.satria.jpro.lastsubmission.data.source.remote.response.MovieResponse
import com.satria.jpro.lastsubmission.data.source.remote.response.TvShowResponse
import com.satria.jpro.lastsubmission.data.source.remote.vo.ApiResponse
import com.satria.jpro.lastsubmission.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.await
import javax.inject.Inject
//copyright satria junanda//
class RemoteDataSource @Inject constructor(private val catalogApiService: ApiService) {

    fun getNowPlayingMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovieResponse = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        CoroutineScope(IO).launch {
            try {
                val response = catalogApiService.getNowPlayingMovies().await()
                resultMovieResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovieResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovieResponse
    }

    fun getNowPlayingComingSoon(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovieResponse = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        CoroutineScope(IO).launch {
            try {
                val response = catalogApiService.getComingSoon().await()
                resultMovieResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovieResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovieResponse
    }


    fun getTvShowOnTheAir(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvShowResponse = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        CoroutineScope(IO).launch {
            try {
                val response = catalogApiService.getTvShowOnTheAir().await()
                resultTvShowResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultTvShowResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultTvShowResponse
    }

}
package com.satria.jpro.lastsubmission.data.source.remote.api
//copyright satria junanda//
import com.satria.jpro.lastsubmission.BuildConfig
import com.satria.jpro.lastsubmission.data.source.remote.response.ListResponse
import com.satria.jpro.lastsubmission.data.source.remote.response.MovieResponse
import com.satria.jpro.lastsubmission.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {



    @GET("discover/movie")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("sort_by") sort_by: String = BuildConfig.TMDB_MOVIE_SORT
    ) : Call<ListResponse<MovieResponse>>


    @GET("discover/movie")
    fun getTvShowOnTheAir(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("sort_by") sort_by: String = BuildConfig.TMDB_MOVIE_SORT,
        @Query("year") year: String = BuildConfig.TMDB_YEAR
    ) : Call<ListResponse<TvShowResponse>>

    @GET("discover/movie")
    fun getComingSoon(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("sort_by") sort_by: String = BuildConfig.TMDB_MOVIE_SORT,
        @Query("year") year: String = BuildConfig.TMDB_YEAR_NOW
    ) : Call<ListResponse<MovieResponse>>


//    @GET("tv/on_the_air")
//    fun getTvShowOnTheAir(
//        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
//    ) : Call<ListResponse<TvShowResponse>>

}
package com.movie.app.data.roomDb.repository

import androidx.lifecycle.LiveData
import com.crashlytics.android.Crashlytics
import com.movie.app.data.responseBean.MovieMapper
import com.movie.app.data.model.Resource
import com.movie.app.data.network.MovieResponse
import com.movie.app.data.roomDb.MovieDao
import com.movie.app.data.roomDb.entity.Movie
import com.movie.app.data.webApi.ApiRequestService
import com.movie.app.data.webApi.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SearchMovieRepository @Inject
constructor(
    val mApiRequestService: ApiRequestService,
    val movieDao: MovieDao
) : Repository {

    var SORT_BY_POPULARITY: Int = 1
    var SORT_BY_RATING: Int = 2

    fun loadMovies(page: Int, sortBy: Int): LiveData<Resource<List<Movie>>> {

        return object : NetworkRepository<List<Movie>, MovieResponse, MovieMapper>(sortBy) {

            override fun saveFetchData(items: MovieResponse) {
                for (item in items.results) {
                    item.page = page
                }
                movieDao.insertMovieList(movies = items.results)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Movie>> {

                when (sortBy) {
                    SORT_BY_RATING ->
                        return movieDao.getTopRatingMovieList(page_ = page)
                    SORT_BY_POPULARITY ->
                        return movieDao.getPopularMovieList(page_ = page)
                    else ->
                        return movieDao.getMovieList(page_ = page)
                }
            }

            override fun fetchService(sortBy: Int): LiveData<ApiResponse<MovieResponse>> {

                when (sortBy) {
                    SORT_BY_RATING ->
                        return mApiRequestService.fetchSortByRatingMovie(page = page)
                    SORT_BY_POPULARITY ->
                        return mApiRequestService.fetchSortByPopularityMovie(page = page)
                    else ->
                        return mApiRequestService.fetchMovieList(page = page)
                }

            }


            override fun mapper(): MovieMapper {
                return MovieMapper()
            }

            override fun onFetchFailed(message: String?) {
                Crashlytics.log("onFetchFailed $message")
            }
        }.asLiveData()
    }


}

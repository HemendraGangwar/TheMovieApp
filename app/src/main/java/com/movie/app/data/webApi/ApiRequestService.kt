package com.movie.app.data.webApi

import androidx.lifecycle.LiveData
import com.movie.app.common.constants.AppConstant
import com.movie.app.data.network.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * Created by hemendrag on 15/07/2019.
 */
interface ApiRequestService {
  /**
   *   Search movies by different types of data like average rating, number of votes, genres and certifications.
   *  @param [page] Specify the page of results to query.
   *  @return [MovieResponse] response
   */
  @GET("/3/discover/movie?api_key=${AppConstant.API_KEY}&language=en")
  fun fetchMovieList(@Query("page") page: Int): LiveData<ApiResponse<MovieResponse>>

    /**
     *   Search movies by sorting order rating
     *  @param [page] Specify the page of results to query.
     *  @return [MovieResponse] response
     */
    @GET("/3/discover/movie?api_key=${AppConstant.API_KEY}&language=en&sort_by=vote_average.desc")
    fun fetchSortByRatingMovie(@Query("page") page: Int): LiveData<ApiResponse<MovieResponse>>

    /**
     *   Search movies by sorting order popularity
     *  @param [page] Specify the page of results to query.
     *  @return [MovieResponse] response
     */
    @GET("/3/discover/movie?api_key=${AppConstant.API_KEY}&language=en&sort_by=popularity.desc")
    fun fetchSortByPopularityMovie(@Query("page") page: Int): LiveData<ApiResponse<MovieResponse>>

}

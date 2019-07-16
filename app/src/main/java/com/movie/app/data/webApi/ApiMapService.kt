package com.movie.app.data.webApi
/**
 * Created by hemendrag on 15/07/2019.
 */
object ApiMapService {
  private const val PHOTO_PATH = "https://image.tmdb.org/t/p/w342"
  private const val BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"

  fun getPosterPath(posterPath: String): String {
    return PHOTO_PATH + posterPath
  }

  fun getBackdropPath(backdropPath: String): String {
    return BACKDROP_PATH + backdropPath
  }

}

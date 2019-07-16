package com.movie.app.data.network

import com.movie.app.data.model.NetworkResponseModel
import com.movie.app.data.roomDb.entity.Movie


data class MovieResponse(
  val page: Int,
  val results: List<Movie>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel

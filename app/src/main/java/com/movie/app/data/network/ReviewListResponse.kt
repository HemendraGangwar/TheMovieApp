package com.movie.app.data.network

import com.movie.app.data.model.NetworkResponseModel
import com.movie.app.data.model.Review

class ReviewListResponse(
  val id: Int,
  val page: Int,
  val results: List<Review>,
  val total_pages: Int,
  val total_results: Int
) : NetworkResponseModel

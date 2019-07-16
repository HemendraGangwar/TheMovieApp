package com.movie.app.data.responseBean

import com.movie.app.data.model.NetworkResponseModel
import com.movie.app.data.model.Review

class ReviewResponse(
  val id: Int,
  val page: Int,
  val results: List<Review>,
  val total_pages: Int,
  val total_results: Int
) : NetworkResponseModel

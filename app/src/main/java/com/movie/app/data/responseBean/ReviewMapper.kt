package com.movie.app.data.responseBean


class ReviewMapper : NetworkMapper<ReviewResponse> {
  override fun onLastPage(response: ReviewResponse): Boolean {
    return true
  }
}

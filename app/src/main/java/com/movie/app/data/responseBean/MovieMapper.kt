package com.movie.app.data.responseBean

import com.crashlytics.android.Crashlytics
import com.movie.app.data.network.MovieResponse


class MovieMapper : NetworkMapper<MovieResponse> {
  override fun onLastPage(response: MovieResponse): Boolean {
    Crashlytics.log("loadPage : ${response.page}/${response.total_pages}")
    return response.page > response.total_pages
  }
}

package com.movie.app.data.responseBean

class KeywordMapper : NetworkMapper<KeywordResponse> {
  override fun onLastPage(response: KeywordResponse): Boolean {
    return true
  }
}

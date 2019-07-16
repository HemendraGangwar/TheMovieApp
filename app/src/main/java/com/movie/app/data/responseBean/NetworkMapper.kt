package com.movie.app.data.responseBean

import com.movie.app.data.model.NetworkResponseModel

interface NetworkMapper<in FROM : NetworkResponseModel> {
  fun onLastPage(response: FROM): Boolean
}

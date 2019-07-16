package com.skydoves.themovies.mappers

import com.movie.app.data.responseBean.NetworkMapper
import com.movie.app.data.responseBean.VideoResponse

class VideoMapper : NetworkMapper<VideoResponse> {
  override fun onLastPage(response: VideoResponse): Boolean {
    return true
  }
}

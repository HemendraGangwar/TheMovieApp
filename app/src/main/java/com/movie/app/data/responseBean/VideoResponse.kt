package com.movie.app.data.responseBean

import com.movie.app.data.model.NetworkResponseModel
import com.movie.app.data.model.Video


data class VideoResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel

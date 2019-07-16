package com.skydoves.themovies.models.network

import com.movie.app.data.model.NetworkResponseModel
import com.movie.app.data.model.Video

data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel

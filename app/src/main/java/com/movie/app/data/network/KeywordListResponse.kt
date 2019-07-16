package com.movie.app.data.network

import com.movie.app.data.model.Keyword
import com.movie.app.data.model.NetworkResponseModel

data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel

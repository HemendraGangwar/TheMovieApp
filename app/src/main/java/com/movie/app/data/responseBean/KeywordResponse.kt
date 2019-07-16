package com.movie.app.data.responseBean

import com.movie.app.data.model.Keyword
import com.movie.app.data.model.NetworkResponseModel


data class KeywordResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel

package com.movie.app.data.webApi

import okhttp3.Interceptor
import okhttp3.Response
/**
 * Created by hemendrag on 15/07/2019.
 */
internal class DataInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalUrl = originalRequest.url()
    val url = originalUrl.newBuilder()
      .build()

    val requestBuilder = originalRequest.newBuilder().url(url)
    val request = requestBuilder.build()
    return chain.proceed(request)
  }
}

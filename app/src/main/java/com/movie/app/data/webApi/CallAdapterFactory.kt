package com.movie.app.data.webApi

import androidx.lifecycle.LiveData
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
/**
 * Created by hemendrag on 15/07/2019.
 */
class CallAdapterFactory : CallAdapter.Factory() {

  override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): LiveDataCallAdapter<*>? {
    if (getRawType(returnType) != LiveData::class.java) {
      return null
    }
    val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
    val rawObservableType = getRawType(observableType)
    if (rawObservableType != ApiResponse::class.java) {
      throw IllegalArgumentException("type must be a resource")
    }
    if (observableType !is ParameterizedType) {
      throw IllegalArgumentException("resource must be parameterized")
    }
    val bodyType = getParameterUpperBound(0, observableType)
    return LiveDataCallAdapter<Type>(bodyType)
  }
}

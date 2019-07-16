package com.movie.app.utils

import androidx.lifecycle.LiveData
/**
 * Created by hemendrag on 14/07/2019.
 */
class AbsentLiveData<T> : LiveData<T>() {
  init {
    postValue(null)
  }

  companion object {
    fun <T> create() = AbsentLiveData<T>()
  }
}

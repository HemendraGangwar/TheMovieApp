package com.movie.app.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crashlytics.android.Crashlytics
import com.movie.app.data.roomDb.repository.MovieRepository
import javax.inject.Inject
/**
 * Created by hemendrag on 14/07/2019.
 */
class DetailViewModel @Inject
constructor(private val repository: MovieRepository) : ViewModel() {

  private val movieIdLiveData: MutableLiveData<Int> = MutableLiveData()

  fun postMovieId(id: Int) = movieIdLiveData.postValue(id)
}

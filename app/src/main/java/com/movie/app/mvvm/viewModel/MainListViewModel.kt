package com.movie.app.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.crashlytics.android.Crashlytics
import com.movie.app.data.model.Resource
import com.movie.app.data.roomDb.entity.Movie
import com.movie.app.data.roomDb.repository.SearchMovieRepository
import com.movie.app.utils.AbsentLiveData
import javax.inject.Inject
/**
 * Created by hemendrag on 14/07/2019.
 */
class MainListViewModel @Inject
constructor(
  private val searchMovieRepository: SearchMovieRepository
) : ViewModel() {
  var sortBy : Int = 0
  private var moviePageLiveData: MutableLiveData<Int> = MutableLiveData()
  val movieListLiveData: LiveData<Resource<List<Movie>>>

  init {
     movieListLiveData = moviePageLiveData.switchMap {
      moviePageLiveData.value?.let { searchMovieRepository.loadMovies(it, sortBy) }
        ?: AbsentLiveData.create()
    }
  }
  fun getMovieListValues() = movieListLiveData.value

  fun postMoviePage(page: Int) = moviePageLiveData.postValue(page)

}

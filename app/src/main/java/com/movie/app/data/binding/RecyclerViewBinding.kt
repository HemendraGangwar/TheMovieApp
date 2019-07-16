package com.movie.app.data.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.movie.app.common.factoryExtensions.bindResource
import com.movie.app.data.model.Resource
import com.movie.app.data.roomDb.entity.Movie
import com.movie.app.mvvm.adapter.MovieListAdapter

@BindingAdapter("movieList")
fun bindAdapterMovieList(view: RecyclerView, resource: Resource<List<Movie>>?) {
  view.bindResource(resource) {
    if (resource != null) {
      val adapter = view.adapter as? MovieListAdapter
      adapter?.addMovieList(resource)
    }
  }
}
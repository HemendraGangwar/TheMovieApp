package com.movie.app.data.binding

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.movie.app.common.factoryExtensions.bindResource
import com.movie.app.common.factoryExtensions.requestGlideListener
import com.movie.app.common.factoryExtensions.visible
import com.movie.app.data.model.Resource
import com.movie.app.data.network.PersonDetail
import com.movie.app.data.roomDb.entity.Movie
import com.movie.app.data.webApi.ApiMapService

@BindingAdapter("visibilityByResource")
fun bindVisibilityByResource(view: View, resource: Resource<List<Any>>?) {
  view.bindResource(resource) {
    if (resource?.data?.isNotEmpty()!!) {
      view.visible()
    }
  }
}

@BindingAdapter("biography")
fun bindBiography(view: TextView, resource: Resource<PersonDetail>?) {
  view.bindResource(resource) {
    view.text = resource?.data?.biography
  }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("bindReleaseDate")
fun bindReleaseDate(view: TextView, movie: Movie) {
  view.text = "Release Date : ${movie.release_date}"
}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, movie: Movie) {
  if (movie.backdrop_path != null) {
    Glide.with(view.context).load(ApiMapService.getBackdropPath(movie.backdrop_path))
      .listener(view.requestGlideListener())
      .into(view)
  } else {
    Glide.with(view.context).load(ApiMapService.getBackdropPath(movie.poster_path!!))
      .listener(view.requestGlideListener())
      .into(view)
  }
}


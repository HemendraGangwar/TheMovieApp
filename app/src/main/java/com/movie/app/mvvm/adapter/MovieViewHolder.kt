package com.movie.app.mvvm.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.movie.app.data.roomDb.entity.Movie
import com.movie.app.data.webApi.ApiMapService
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.layout_row_movie.view.*
/**
 * To increase the speed at which their RecyclerView renders data
 * Created by hemendrag on 14/07/2019.
 */
class MovieViewHolder(view: View, private val delegate: Delegate)
  : BaseViewHolder(view) {

  interface Delegate {
    fun onItemClick(movie: Movie)
  }

  private lateinit var movie: Movie

  @Throws(Exception::class)
  override fun bindData(data: Any) {
    if (data is Movie) {
      movie = data
      drawItem()
    }
  }

  /**
   * here thisd method is used for assigning data into views
   */
  private fun drawItem() {
    itemView.run {
      item_poster_title.text = movie.title
      movie.poster_path?.let {
        Glide.with(context)
          .load(ApiMapService.getPosterPath(it))
          .listener(GlidePalette.with(ApiMapService.getPosterPath(it))
            .use(BitmapPalette.Profile.VIBRANT)
            .intoBackground(item_poster_palette)
            .crossfade(true))
          .into(item_poster_post)
      }
    }
  }

  override fun onClick(v: View?) {
    delegate.onItemClick(movie)
  }

  override fun onLongClick(v: View?) = false
}

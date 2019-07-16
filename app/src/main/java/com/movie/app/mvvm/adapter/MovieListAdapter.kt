package com.movie.app.mvvm.adapter

import android.view.View
import com.movie.app.R
import com.movie.app.data.model.Resource
import com.movie.app.data.roomDb.entity.Movie
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow


/** Adapter is a bridge between UI component and data source
 * that helps us to fill data in UI component.
 * It holds the data and send the data to an Adapter view
 * then view can takes the data from the adapter view and shows the data on different views
 * Created by hemendrag on 14/07/2019.
 */
class MovieListAdapter(private val delegate: MovieViewHolder.Delegate)
  : BaseAdapter() {

  init {
    addSection(ArrayList<Movie>())
  }

  /**
   * add resource layout for adapter
   */
  override fun layout(sectionRow: SectionRow): Int {
    return R.layout.layout_row_movie
  }

  override fun viewHolder(layout: Int, view: View): BaseViewHolder {
    return MovieViewHolder(view, delegate)
  }

  /**
   * add data on list
   */
  fun addMovieList(resource: Resource<List<Movie>>) {
    resource.data?.let {
      sections()[0].addAll(it)
      notifyDataSetChanged()
    }
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

}

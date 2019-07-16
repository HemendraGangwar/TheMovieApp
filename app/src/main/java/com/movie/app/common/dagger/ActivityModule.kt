package com.movie.app.common.dagger

import com.movie.app.mvvm.activity.DetailActivity
import com.movie.app.mvvm.activity.MovieListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * For Activity dependency
 * Created by hemendrag on 15/07/2019.
 */
@Suppress("unused")
@Module
abstract class ActivityModule {

  @ContributesAndroidInjector
  internal abstract fun contributeMovieDetailActivity(): MovieListActivity

  @ContributesAndroidInjector
  internal abstract fun contributeTvDetailActivity(): DetailActivity

}

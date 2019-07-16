package com.movie.app.application

import com.movie.app.common.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric


/**
 * This class is a base class for maintaining global application state
 * Created by hemendrag on 14/07/2019.
 */
class TheMovieApplication : DaggerApplication() {

  private val appComponent = DaggerAppComponent.builder()
    .application(this)
    .build()

  override fun onCreate() {
    super.onCreate()
    // Fabric for dagger
    appComponent.inject(this)
    // Fabric for crash analytics
    Fabric.with(this, Crashlytics())
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return appComponent
  }

}

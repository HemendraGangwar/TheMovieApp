package com.movie.app.data.roomDb.repository

import com.crashlytics.android.Crashlytics
import com.movie.app.data.roomDb.MovieDao
import com.movie.app.data.webApi.MovieService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject
constructor(val service: MovieService, val movieDao: MovieDao) : Repository {

    init {
        Crashlytics.log("Injection MovieRepository")
    }

}

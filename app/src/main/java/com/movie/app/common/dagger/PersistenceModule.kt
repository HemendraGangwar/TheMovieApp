package com.movie.app.common.dagger

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.movie.app.data.roomDb.AppDatabase
import com.movie.app.data.roomDb.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * For Db dependency
 * Created by hemendrag on 15/07/2019.
 */
@Module
class PersistenceModule {

  @Provides
  @Singleton
  fun provideDatabase(@NonNull application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "Movies.db").allowMainThreadQueries().build()
  }

  @Provides
  @Singleton
  fun provideMovieDao(@NonNull database: AppDatabase): MovieDao {
    return database.movieDao()
  }

}

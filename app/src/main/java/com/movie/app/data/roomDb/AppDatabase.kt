package com.movie.app.data.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.movie.app.data.converters.IntConverter
import com.movie.app.data.converters.KwConverter
import com.movie.app.data.converters.ReviewConverter
import com.movie.app.data.roomDb.entity.Movie
import com.skydoves.themovies.utils.StringConverter

@Database(entities = [(Movie::class)], version = 1, exportSchema = false)
@TypeConverters(
    value = [(StringConverter::class), (IntConverter::class),
        (KwConverter::class), (ReviewConverter::class)]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    companion object {

        // solution and to use a dependency injection framework instead.
        private var instance: AppDatabase? = null
        @Synchronized
        fun get(context: Context): AppDatabase {
            return instance ?: Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "TMDb")
                .allowMainThreadQueries()
                .build()
                .also { instance = it }
        }
    }

}

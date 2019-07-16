package com.movie.app.data.roomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.movie.app.data.roomDb.entity.Movie
import androidx.sqlite.db.SupportSQLiteQuery
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery





@Dao
interface MovieDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovieList(movies: List<Movie>)

  @Update
  fun updateMovie(movie: Movie)

  @Query("SELECT * FROM MOVIE WHERE id = :id_")
  fun getMovie(id_: Int): Movie

  @Query("SELECT * FROM Movie WHERE page = :page_")
  fun getMovieList(page_: Int): LiveData<List<Movie>>

  @Query("SELECT * FROM Movie WHERE page = :page_  ORDER BY popularity desc")
  fun getPopularMovieList(page_: Int): LiveData<List<Movie>>

  @Query("SELECT * FROM Movie WHERE page = :page_  ORDER BY vote_average desc")
  fun getTopRatingMovieList(page_: Int): LiveData<List<Movie>>

  @Query("UPDATE Movie SET is_like = :like WHERE id = :id")
  fun updateLike(like: Int, id: Int)

  @Query("SELECT is_like from Movie WHERE id = :id")
  fun getLike(id: Int) : Int

}

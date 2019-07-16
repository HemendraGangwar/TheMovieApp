package com.movie.app.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.movie.app.data.model.Review
/**
 * It allows you to persists a specific custom type into a database
 * Created by hemendrag on 15/07/2019.
 */
open class ReviewConverter {
  @TypeConverter
  fun fromString(value: String): List<Review>? {
    val listType = object : TypeToken<List<Review>>() {}.type
    return Gson().fromJson<List<Review>>(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<Review>?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}

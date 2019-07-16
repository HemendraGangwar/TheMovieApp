package com.movie.app.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * It allows you to persists a specific custom type into a database
 * Created by hemendrag on 15/07/2019.
 */
open class IntConverter {
  @TypeConverter
  fun fromString(value: String): List<Int>? {
    val listType = object : TypeToken<List<Int>>() {}.type
    return Gson().fromJson<List<Int>>(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<Int>): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}

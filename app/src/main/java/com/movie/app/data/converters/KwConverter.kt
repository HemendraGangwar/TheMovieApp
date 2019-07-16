package com.movie.app.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.movie.app.data.model.Keyword

/**
 * It allows you to persists a specific custom type into a database
 * Created by hemendrag on 15/07/2019.
 */
open class KwConverter {
  @TypeConverter
  fun fromString(value: String): List<Keyword>? {
    val listType = object : TypeToken<List<Keyword>>() {}.type
    return Gson().fromJson<List<Keyword>>(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<Keyword>?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}

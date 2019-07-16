package com.skydoves.themovies.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * It allows you to persists a specific custom type into a database
 * Created by hemendrag on 15/07/2019.
 */
open class StringConverter {
  @TypeConverter
  fun fromString(value: String): List<String>? {
    val listType = object : TypeToken<List<String>>() {}.type
    return Gson().fromJson<List<String>>(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<String>?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}

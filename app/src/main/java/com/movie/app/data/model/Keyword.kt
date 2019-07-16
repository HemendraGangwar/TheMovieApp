package com.movie.app.data.model

import android.os.Parcel
import android.os.Parcelable

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "unused")
data class Keyword(
  val id: Int,
  val name: String
) : Parcelable {
  constructor(source: Parcel) : this(
    source.readInt(),
    source.readString()
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
    writeInt(id)
    writeString(name)
  }

  companion object {
    @JvmField
    val CREATOR: Parcelable.Creator<Keyword> = object : Parcelable.Creator<Keyword> {
      override fun createFromParcel(source: Parcel): Keyword = Keyword(source)
      override fun newArray(size: Int): Array<Keyword?> = arrayOfNulls(size)
    }
  }
}

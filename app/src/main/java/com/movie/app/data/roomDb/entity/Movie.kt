package com.movie.app.data.roomDb.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.movie.app.data.model.Keyword
import com.movie.app.data.model.Review
import com.movie.app.data.model.Video

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "unused")
@Entity(primaryKeys = [("id")])
data class Movie(
    var page: Int,
    var keywords: List<Keyword>? = ArrayList(),
    var reviews: List<Review>? = ArrayList(),
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float,
    val is_like: Int
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        ArrayList<Keyword>().apply { source.readList(this, Keyword::class.java.classLoader) },
        ArrayList<Review>().apply { source.readList(this, Review::class.java.classLoader) },
        source.readString(),
        1 == source.readInt(),
        source.readString(),
        source.readString(),
        ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readFloat(),
        source.readInt(),
        1 == source.readInt(),
        source.readFloat(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(page)
        writeList(keywords)
        writeList(reviews)
        writeString(poster_path)
        writeInt((if (adult) 1 else 0))
        writeString(overview)
        writeString(release_date)
        writeList(genre_ids)
        writeInt(id)
        writeString(original_title)
        writeString(original_language)
        writeString(title)
        writeString(backdrop_path)
        writeFloat(popularity)
        writeInt(vote_count)
        writeInt((if (video) 1 else 0))
        writeFloat(vote_average)
        writeInt(is_like)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}

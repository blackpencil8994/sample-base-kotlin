package com.oanhltk.sample_base_kotlin.data.entity

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.TypeConverters
import com.oanhltk.sample_base_kotlin.data.local.CastListTypeConverter
import com.oanhltk.sample_base_kotlin.data.local.GenreListTypeConverter
import com.oanhltk.sample_base_kotlin.utils.AppConstants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(primaryKeys = ["id"])
data class Movie(
    @Json(name = "adult")
    val adult: Boolean?,

    @Json(name = "backdrop_path")
    var backdropPath: String?,

    @Json(name = "id")
    val id: Int,

    @Json(name = "original_language")
    val originalLanguage: String?,

    @Json(name = "overview")
    val overview: String?,

    @Json(name = "popularity")
    val popularity: Double?,

    @Json(name = "title")
    val title: String?,

    @Json(name = "video")
    val video: Boolean?,

    @Json(name = "poster_path")
    var posterPath: String?,

    @Json(name = "vote_average")
    val voteAverage: Double?,

    @Json(name = "status")
    var status: String?,

    @Json(name = "imdb_id")
    var imdbId: String?,

    @Json(name = "runtime")
    val runtime: Int?,

    @Json(name = "release_date")
    var releaseDate: String?,

    @Json(name = "is_favorite")
    var isFavorite: Boolean?,

    @Json(name = "genres")
    @TypeConverters(GenreListTypeConverter::class)
    val genres: List<Genre> = ArrayList(),

    @TypeConverters(CastListTypeConverter::class)
    var cast: List<Cast> = ArrayList()
) {
    fun getFormattedPosterPath(): String? {
        if (posterPath != null && !posterPath!!.startsWith("http")) {
            posterPath = String.format(AppConstants.IMAGE_URL, posterPath)
        }
        posterPath?.let { Log.d("getFormattedPosterPath", it) }
        return posterPath
    }

    fun getFormattedBackdropPath(): String? {
        if (backdropPath != null && !backdropPath!!.startsWith("http")) {
            backdropPath = String.format(AppConstants.IMAGE_URL, backdropPath)
        }
        backdropPath?.let { Log.d("getFormattedBackdrop", it) }
        return backdropPath
    }

    fun getListGenreInString(): String {
        var listGenre = ""
        if (genres?.isNotEmpty()) {
            listGenre = genres.map {
                it.name
            }.joinToString(separator = "/")
        }
        return listGenre
    }

    fun getRuntimeInString(): String {
        return runtime?.let {
            "$it minutes"
        } ?: ""
    }
}


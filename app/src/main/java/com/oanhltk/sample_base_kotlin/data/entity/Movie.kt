package com.oanhltk.sample_base_kotlin.data.entity

import android.util.Log
import com.oanhltk.sample_base_kotlin.utils.AppConstants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(

    @Json(name = "adult")
    val adult: Boolean?,

    @Json(name = "backdrop_path")
    val backdropPath: String?,

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
    val voteAverage: Double?
) {
    fun getFormattedPosterPath(): String? {
        if (posterPath != null && !posterPath!!.startsWith("http")) {
            posterPath = String.format(AppConstants.IMAGE_URL, posterPath)
        }
        posterPath?.let { Log.d("getFormattedPosterPath", it) }
        return posterPath
    }
}
package com.oanhltk.sample_base_kotlin.data.remote
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.squareup.moshi.Json

data class MovieResponse(
    @Json(name = "page")
    val page: Long,

    @Json(name = "results")
    val results: List<Movie>,

    @Json(name = "total_results")
    val total_results: Int,

    @Json(name = "total_pages")
    val total_pages: Int)

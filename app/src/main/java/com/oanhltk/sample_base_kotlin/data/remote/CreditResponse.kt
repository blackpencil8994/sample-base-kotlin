package com.oanhltk.sample_base_kotlin.data.remote

import com.oanhltk.sample_base_kotlin.data.entity.Cast
import com.squareup.moshi.Json

data class CreditResponse(
        @Json(name = "id")
        val page: Int,

        @Json(name = "cast")
        val results: List<Cast>
)

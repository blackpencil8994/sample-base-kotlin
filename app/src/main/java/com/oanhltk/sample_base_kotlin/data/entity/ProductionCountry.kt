package com.oanhltk.sample_base_kotlin.data.entity

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(primaryKeys = ["id"])
data class ProductionCountry(
        @Json(name = "id")
        val id: Int,

        @Json(name = "iso_3166_1")
        val iso31661: String?
)

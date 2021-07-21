package com.oanhltk.sample_base_kotlin.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(primaryKeys = ["id"])
data class Genre(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String?
)

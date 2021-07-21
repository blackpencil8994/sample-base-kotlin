package com.oanhltk.sample_base_kotlin.data.local

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"])
data class MovieGenreCrossRef(
        val movieId: Int,
        val genreId: Int
)
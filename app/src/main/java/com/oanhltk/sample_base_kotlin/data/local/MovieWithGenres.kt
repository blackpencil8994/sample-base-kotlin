package com.oanhltk.sample_base_kotlin.data.local

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.oanhltk.sample_base_kotlin.data.entity.Genre
import com.oanhltk.sample_base_kotlin.data.entity.Movie

data class MovieWithGenres (
        @Embedded val movie: Movie,
        @Relation(
                parentColumn = "movieId",
                entityColumn = "genreId",
                associateBy = Junction(MovieGenreCrossRef::class)
        )
        val genres: List<Genre>
        )
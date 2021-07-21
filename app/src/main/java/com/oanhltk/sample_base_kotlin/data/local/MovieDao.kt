package com.oanhltk.sample_base_kotlin.data.local

import androidx.room.*
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movie): Int

    @Query("SELECT * FROM `Movie` where id = :id")
    fun getMovieById(id: Int?): Movie

    @Query("SELECT * FROM `Movie` where id = :id")
    fun getMovieDetailById(id: Int?): Flowable<Movie>

//    @Query("SELECT * FROM `Movie` where page = :page")
//    fun getMoviesByPage(page: Long): List<Movie>

    @Query("SELECT * FROM `Movie`")
    fun getAllMoviesByPage(): List<Movie>
}
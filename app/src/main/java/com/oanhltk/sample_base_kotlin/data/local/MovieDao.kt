package com.oanhltk.sample_base_kotlin.data.local

import androidx.room.*
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: List<Movie>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Long // return long value which is the new rowId for the inserted item

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movie): Int // return int value which is the number of row updated successfully

    @Query("SELECT * FROM `Movie` where id = :id")
    fun getMovieById(id: Int?): Movie

    @Query("SELECT * FROM `Movie` where isFavorite = 1")
    fun getFavoriteMovies(): List<Movie>

//    @Query("SELECT * FROM `Movie` where page = :page")
//    fun getMoviesByPage(page: Long): List<Movie>

    @Query("SELECT * FROM `Movie`")
    fun getAllMoviesByPage(): List<Movie>
}
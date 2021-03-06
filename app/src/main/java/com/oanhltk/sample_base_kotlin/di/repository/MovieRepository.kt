package com.oanhltk.sample_base_kotlin.di.repository

import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.data.remote.Resource
import io.reactivex.Observable
import javax.inject.Singleton

@Singleton
interface MovieRepository {
    fun loadMoviesByType() : Observable<Resource<List<Movie>>>

    fun getDetailMovie(id: Int) : Observable<Resource<Movie>>

    fun loadFavoriteMovies() : Observable<Resource<List<Movie>>>

    fun saveMovieFavorite(movie: Movie): Observable<Resource<Boolean>>


}
package com.oanhltk.sample_base_kotlin.api

import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.data.remote.CreditResponse
import com.oanhltk.sample_base_kotlin.data.remote.MovieResponse
import com.oanhltk.sample_base_kotlin.data.remote.Resource
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movie/popular?language=en-US&region=US&page=1")
    fun fetchMoviesByType():  Observable<MovieResponse>


    @GET("movie/{movieId}")
    fun fetchMovieDetail(@Path("movieId") movieId: Int): Observable<Movie>

    @GET("movie/{movieId}/credits")
    fun fetchCastDetail(@Path("movieId") movieId: Int): Observable<CreditResponse>
}
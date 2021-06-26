package com.oanhltk.sample_base_kotlin.api

import com.oanhltk.sample_base_kotlin.data.remote.MovieResponse
import com.oanhltk.sample_base_kotlin.data.remote.Resource
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieService {

    @GET("movie/popular?language=en-US&region=US&page=1")
    fun fetchMoviesByType():  Observable<MovieResponse>
}
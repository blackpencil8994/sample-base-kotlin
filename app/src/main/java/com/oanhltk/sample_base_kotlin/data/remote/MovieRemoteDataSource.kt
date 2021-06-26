package com.oanhltk.sample_base_kotlin.data.remote

import com.oanhltk.sample_base_kotlin.api.MovieService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor (
    val movieApi: MovieService,
) {
    fun loadMoviesByType(): Observable<MovieResponse> {
        return movieApi.fetchMoviesByType()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
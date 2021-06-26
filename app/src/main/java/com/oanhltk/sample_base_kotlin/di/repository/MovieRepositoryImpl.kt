package com.oanhltk.sample_base_kotlin.di.repository

import com.oanhltk.sample_base_kotlin.data.remote.MovieRemoteDataSource
import com.oanhltk.sample_base_kotlin.data.remote.MovieResponse
import com.oanhltk.sample_base_kotlin.data.remote.NetworkBoundResource
import com.oanhltk.sample_base_kotlin.data.remote.Resource
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
): MovieRepository {
    override fun loadMoviesByType():Observable<Resource<MovieResponse>> {

        return object : NetworkBoundResource<MovieResponse>(){
            override fun shouldFetch(): Boolean {
                return true
            }

            override fun createCall(): Observable<Resource<MovieResponse>> {
                return movieRemoteDataSource.loadMoviesByType()
                        .flatMap{movieResponse ->
                            Observable.just(
                                    if (movieResponse == null) Resource.error("", MovieResponse(1, emptyList(), 0, 1))
                                    else Resource.success(movieResponse)
                            )
                        }
            }

        }.getAsObservable()
    }
}
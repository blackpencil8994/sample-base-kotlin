package com.oanhltk.sample_base_kotlin.di.repository

import android.content.Context
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.data.local.MovieDao
import com.oanhltk.sample_base_kotlin.data.remote.MovieRemoteDataSource
import com.oanhltk.sample_base_kotlin.data.remote.MovieResponse
import com.oanhltk.sample_base_kotlin.data.remote.NetworkBoundResource
import com.oanhltk.sample_base_kotlin.data.remote.Resource
import com.oanhltk.sample_base_kotlin.utils.ConnectivityStatus
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
        private val movieRemoteDataSource: MovieRemoteDataSource,
        private val movieDao: MovieDao,
        private val context: Context
    ): MovieRepository {
    override fun loadMoviesByType():Observable<Resource<List<Movie>>> {

        return object : NetworkBoundResource<List<Movie>, MovieResponse>(){
            override fun shouldFetch(): Boolean {
                return ConnectivityStatus.isInternetAvailable(context)
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

            override fun saveCallResult(item: MovieResponse) {
                movieDao.insertMovies(item.results)
            }

            override fun loadFromDb(): Flowable<List<Movie>> {
                val movieEntities = movieDao.getAllMoviesByPage()
                return if (movieEntities == null || movieEntities.isEmpty()) {
                    Flowable.empty()
                } else Flowable.just(movieEntities)
            }

        }.getAsObservable()
    }
}
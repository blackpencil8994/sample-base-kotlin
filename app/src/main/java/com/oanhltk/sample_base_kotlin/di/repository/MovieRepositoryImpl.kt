package com.oanhltk.sample_base_kotlin.di.repository

import android.content.Context
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.data.local.MovieDao
import com.oanhltk.sample_base_kotlin.data.remote.*
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
) : MovieRepository {
    override fun loadMoviesByType(): Observable<Resource<List<Movie>>> {

        return object : NetworkBoundResource<List<Movie>, MovieResponse>() {
            override fun shouldFetch(): Boolean {
                return ConnectivityStatus.isInternetAvailable(context)
            }

            override fun createCall(): Observable<Resource<MovieResponse>> {
                return movieRemoteDataSource.loadMoviesByType()
                        .flatMap { movieResponse ->
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

    override fun getDetailMovie(id: Int): Observable<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, Movie>() {
            override fun saveCallResult(item: Movie) {
                val savedMovie = movieDao.getMovieById(id)
                if (savedMovie != null) {
                    item.isFavorite = savedMovie.isFavorite
                }
                movieDao.insertMovie(item)
            }

            override fun shouldFetch(): Boolean {
                return ConnectivityStatus.isInternetAvailable(context)
            }

            override fun loadFromDb(): Flowable<Movie> {
                val movie = movieDao.getMovieById(id)
                return if (movie == null) {
                    Flowable.empty()
                } else Flowable.just(movie)
            }

            override fun createCall(): Observable<Resource<Movie>> {
                return Observable.combineLatest(
                        movieRemoteDataSource.getDetailMovie(id),
                        movieRemoteDataSource.fetchCastDetail(id),
                        { movie: Movie, creditResponse: CreditResponse ->
                            if (movie == null) Resource.error("", null) else {
                                movie.cast = creditResponse.results
                                Resource.success(movie)
                            }
                        })
            }

        }.getAsObservable()
    }

    override fun loadFavoriteMovies(): Observable<Resource<List<Movie>>> {
        val asObservable: Flowable<List<Movie>>
        val movieEntities = movieDao.getFavoriteMovies()
        asObservable = if (movieEntities == null || movieEntities.isEmpty()) {
            Flowable.empty()
        } else Flowable.just(movieEntities)
        return asObservable
                .toObservable()
                .map { Resource.success(it) }
    }

    override fun saveMovieFavorite(movie: Movie): Observable<Resource<Boolean>> {
        val result = movieDao.updateMovie(movie)
        val asObservable = Flowable.just(movie)
        if(result == 1) {
            return  asObservable
                    .toObservable()
                    .map { Resource.success(true) }
        } else return  asObservable
                .toObservable()
                .map {Resource.success(false)}
    }

}
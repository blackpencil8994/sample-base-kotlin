package com.oanhltk.sample_base_kotlin.ui.main.fragments.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oanhltk.sample_base_kotlin.data.entity.Cast
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.data.remote.Resource
import com.oanhltk.sample_base_kotlin.di.repository.MovieRepository
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
        private val movieRepository: MovieRepository
) : ViewModel() {

    val resourceMovie = MutableLiveData<Resource<Movie>>().apply {
        value = null
    }

    val movie = MutableLiveData<Movie>()

    val changedFavorite =  MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun getDetailMovie(id: Int) {
        resourceMovie.postValue(Resource.loading(null))
        movieRepository.getDetailMovie(id)
                .subscribe({ resource ->
                    resourceMovie.postValue(resource)
                }, {
                    resourceMovie.postValue(Resource.error(it.message.toString(), null))
                    Log.d("OanhLTK..", "onError : $it")
                })
    }

    @SuppressLint("CheckResult")
    fun saveAsFavorite(item: Movie) {
        movieRepository.saveMovieFavorite(item)
                .subscribe({ resource ->
                    if (resource.isSuccess ) {
                        if(resource.data == true) {
                            changedFavorite.postValue(true)
                            movie.postValue(item)
                        }
                    } else {
                        Log.d("OanhLTK..", "onError : ${resource.message}")
                    }
                }, {
//                    detailMovie.postValue(Resource.error(it.message.toString(), null))
                    Log.d("OanhLTK..", "onError : $it")
                })
    }

}
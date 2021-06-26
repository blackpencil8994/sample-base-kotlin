package com.oanhltk.trainingkotlin.ui.main.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oanhltk.sample_base_kotlin.data.remote.MovieResponse
import com.oanhltk.sample_base_kotlin.data.remote.Resource
import com.oanhltk.sample_base_kotlin.di.repository.MovieRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val movieRepository: MovieRepository
) : ViewModel() {

    private val moviesListLiveData = MutableLiveData<Resource<MovieResponse>>()

    @SuppressLint("CheckResult")
    fun loadMoreMovies() {
        moviesListLiveData.postValue(Resource.loading(null))
        movieRepository.loadMoviesByType()
            .subscribe({
                    resource -> moviesListLiveData.postValue(resource)
            }, {
                moviesListLiveData.postValue(Resource.error(it.message.toString(), null))
                Log.d("OanhLTK..", "onError : $it")
            })
    }

    fun getMoviesLiveData() = moviesListLiveData
}
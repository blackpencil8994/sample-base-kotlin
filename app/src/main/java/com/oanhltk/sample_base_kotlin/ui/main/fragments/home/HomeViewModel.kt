package com.oanhltk.sample_base_kotlin.ui.main.fragments.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.data.remote.Resource
import com.oanhltk.sample_base_kotlin.di.repository.MovieRepository
import com.oanhltk.sample_base_kotlin.ui.main.fragments.favorite.FavoriteFragment
import com.oanhltk.sample_base_kotlin.ui.main.fragments.movie.MoviesFragment
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val movieRepository: MovieRepository
) : ViewModel() {

    val favoriteLivedata = MutableLiveData<Resource<List<Movie>>>()

    @SuppressLint("CheckResult")
    fun loadFavoriteMovies() {
        movieRepository.loadFavoriteMovies()
                .subscribe({
                    resource -> favoriteLivedata.postValue(resource)
                }, {
                    favoriteLivedata.postValue(Resource.error(it.message.toString(), null))
                    Log.d("OanhLTK..", "onError : $it")
                })
    }
}
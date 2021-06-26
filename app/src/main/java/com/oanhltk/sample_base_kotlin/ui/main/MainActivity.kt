package com.oanhltk.sample_base_kotlin.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.an.trailers.ui.main.adapter.MoviesListAdapter
import com.oanhltk.sample_base_kotlin.AppController
import com.oanhltk.sample_base_kotlin.R
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.databinding.ActivityMainBinding
import com.oanhltk.trainingkotlin.ui.main.viewmodel.MainViewModel

import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var moviesListViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        (applicationContext as AppController).getAppComponent()?.inject(this)
        initialiseView()
        initialiseViewModel()
    }

    private fun initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        moviesListAdapter = MoviesListAdapter(this)
        binding.moviesList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.moviesList.adapter = moviesListAdapter

    }

    private fun initialiseViewModel() {
        moviesListViewModel.getMoviesLiveData().observe(this, Observer { resource ->
            if (resource.isLoading){
               displayLoader()
            } else {
                if (resource.isSuccess && resource.data!!.results.isNotEmpty()) {
                    updateMoviesList(resource.data.results)
                } else {
                    handleErrorResponse()
                }
            }

        })
        moviesListViewModel.loadMoreMovies()
    }


    private fun displayLoader() {
        binding.moviesList.visibility = View.GONE
        binding.loader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.moviesList.visibility = View.VISIBLE
        binding.loader.visibility = View.GONE
    }

    private fun updateMoviesList(movies: List<Movie>) {
        hideLoader()
        moviesListAdapter.setItems(movies)
    }

    private fun handleErrorResponse() {
        binding.loader.visibility = View.GONE
        binding.moviesList.visibility = View.GONE
    }
}
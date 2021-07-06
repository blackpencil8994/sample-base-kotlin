package com.oanhltk.sample_base_kotlin.ui.main.fragments.movie

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.an.trailers.ui.main.adapter.MoviesListAdapter
import com.oanhltk.sample_base_kotlin.AppController
import com.oanhltk.sample_base_kotlin.R
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.databinding.FragmentMovieBinding
import com.oanhltk.sample_base_kotlin.ui.main.adapter.OnSnapPositionChangeListener
import com.oanhltk.sample_base_kotlin.utils.attachSnapHelperWithListener
import javax.inject.Inject

class MoviesFragment : Fragment() {
    @Inject
    lateinit var moviesViewModel: MoviesViewModel


    private lateinit var binding: FragmentMovieBinding

    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as AppController).getAppComponent()?.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
        initialiseViewModel()
    }

    private fun initialiseView() {

        moviesListAdapter = MoviesListAdapter(this)
        binding.moviesList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.moviesList.adapter = moviesListAdapter

        val snapHelper = LinearSnapHelper()
        val onSnapPositionChangeListener = object : OnSnapPositionChangeListener {
            override fun onSnapPositionChange(position: Int) {
                Log.d("OanhLTK position ", position.toString() )
            }

        }
        binding.moviesList.attachSnapHelperWithListener(snapHelper, onSnapPositionChangeListener)

    }

    private fun initialiseViewModel() {
        moviesViewModel.getMoviesLiveData().observe(viewLifecycleOwner, Observer { resource ->
            if (resource.isLoading){
                displayLoader()
            } else {
                if (resource.isSuccess && resource.data!!.isNotEmpty()) {
                    updateMoviesList(resource.data)
                } else {
                    handleErrorResponse()
                }
            }

        })
        moviesViewModel.loadMoreMovies()
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
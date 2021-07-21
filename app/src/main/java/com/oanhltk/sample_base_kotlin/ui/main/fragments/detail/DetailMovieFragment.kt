package com.oanhltk.sample_base_kotlin.ui.main.fragments.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.an.trailers.ui.main.adapter.MoviesListAdapter
import com.oanhltk.sample_base_kotlin.AppController
import com.oanhltk.sample_base_kotlin.R
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.databinding.FragmentDetailMovieBinding
import com.oanhltk.sample_base_kotlin.ui.main.adapter.CastsListAdapter
import com.oanhltk.sample_base_kotlin.ui.main.fragments.movie.MoviesViewModel
import javax.inject.Inject

class DetailMovieFragment : Fragment() {

    @Inject
    lateinit var detailMovieViewModel: DetailMovieViewModel

    private lateinit var binding: FragmentDetailMovieBinding

    private lateinit var castListAdapter: CastsListAdapter

    private val args: DetailMovieFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as AppController).getAppComponent()?.inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movie, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            root.isClickable = true
        }
        binding.viewModel = detailMovieViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
        initialiseViewModel()
    }

    private fun initialiseView() {
        castListAdapter = CastsListAdapter(this)
        binding.recyclerViewCast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCast.adapter = castListAdapter
        binding.recyclerViewCast.setHasFixedSize(true)

        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initialiseViewModel() {

        detailMovieViewModel.getDetailMovieLiveData().observe(viewLifecycleOwner, Observer { resource ->
            resource?.apply {
                if (isLoading){
//                displayLoader()
                } else {
                    if (isSuccess && data != null) {
                        Log.d("OanhLTK 588228", data.toString())
                    updateMovieDetail(data)
                    } else {
//                    handleErrorResponse()
                    }
                }

            }

        })

        val movieId = args.movieId
        detailMovieViewModel.getDetailMovie(movieId)
    }

    private fun updateMovieDetail(movie: Movie) {
        detailMovieViewModel.movie.postValue(movie)
        if(movie.cast.isNotEmpty()) castListAdapter.setItems(movie.cast)
    }

}
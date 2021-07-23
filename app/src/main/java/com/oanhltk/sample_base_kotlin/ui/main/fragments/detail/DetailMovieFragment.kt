package com.oanhltk.sample_base_kotlin.ui.main.fragments.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.oanhltk.sample_base_kotlin.AppController
import com.oanhltk.sample_base_kotlin.R
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.databinding.FragmentDetailMovieBinding
import com.oanhltk.sample_base_kotlin.di.factory.ViewModelFactory
import com.oanhltk.sample_base_kotlin.ui.main.adapter.CastsListAdapter
import com.oanhltk.sample_base_kotlin.ui.main.fragments.home.HomeViewModel
import javax.inject.Inject

class DetailMovieFragment : Fragment() {

    @Inject
    lateinit var detailMovieViewModel: DetailMovieViewModel

    private val homeViewModel: HomeViewModel by activityViewModels() // => you don't need to pass viewModelFactory since getDefaultViewModelProviderFactory() override in MainActivity

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

        binding.imageFavorite.setOnClickListener {
            saveAsFavoriteMovie()
        }
    }

    private fun initialiseViewModel() {
        detailMovieViewModel.resourceMovie.observe(viewLifecycleOwner, { resource ->
            resource?.apply {
                if (isLoading) {
//                displayLoader()
                } else {
                    if (isSuccess && data != null) {
                        Log.d("OanhLTK", data.toString())
                        updateMovieDetail(data)
                    } else {
//                    handleErrorResponse()
                    }
                }
            }
        })

        detailMovieViewModel.changedFavorite.observe(viewLifecycleOwner, { changedFavorite ->
            changedFavorite?.let {
                if (changedFavorite == true)
                    homeViewModel.loadFavoriteMovies() // => now you are sharing the homeViewModel with FavoriteFragment
            }
        })


        val movieId = args.movieId
        detailMovieViewModel.getDetailMovie(movieId)
    }

    private fun updateMovieDetail(movie: Movie) {
        detailMovieViewModel.movie.postValue(movie)
        if (movie.cast.isNotEmpty()) castListAdapter.setItems(movie.cast)
    }

    private fun saveAsFavoriteMovie() {
        detailMovieViewModel.movie.value?.apply {
                copy().apply {
                if (isFavorite == null) {
                    isFavorite = true
                } else isFavorite = !isFavorite!!
                detailMovieViewModel.saveAsFavorite(this)
            }
        }
    }

}
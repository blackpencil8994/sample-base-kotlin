package com.oanhltk.sample_base_kotlin.ui.main.fragments.favorite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oanhltk.sample_base_kotlin.ui.main.adapter.FavoriteListAdapter
import com.oanhltk.sample_base_kotlin.AppController
import com.oanhltk.sample_base_kotlin.R
import com.oanhltk.sample_base_kotlin.databinding.FragmentFavoriteBinding
import com.oanhltk.sample_base_kotlin.di.factory.ViewModelFactory
import com.oanhltk.sample_base_kotlin.ui.main.fragments.home.HomeViewModel
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels({requireActivity()}) { viewModelFactory }

    private lateinit var binding: FragmentFavoriteBinding

    private var favoriteListAdapter: FavoriteListAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() .applicationContext as AppController).getAppComponent()?.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
        initialiseViewModel()
    }

    private fun initialiseView() {
        if(favoriteListAdapter == null) {
            favoriteListAdapter = FavoriteListAdapter()
            favoriteListAdapter?.onItemClick = {
                val bundle = bundleOf("movieId" to it.id)
                findNavController().navigate(R.id.action_go_detail_movie, bundle)
            }
        }
        binding.moviesList .layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.moviesList.adapter = favoriteListAdapter
    }

    private fun initialiseViewModel() {
        homeViewModel.favoriteLivedata.observe(viewLifecycleOwner, Observer { resource ->
            if (resource.isLoading){
//                displayLoader()
            } else {
                if (resource.isSuccess && resource.data!!.isNotEmpty()) {
                    favoriteListAdapter?.setItems(resource.data)
                } else {
//                    handleErrorResponse()
                }
            }

        })
        if(homeViewModel.favoriteLivedata.value?.data?.isNotEmpty() == true) {
            Log.d("OanhLTK", homeViewModel.favoriteLivedata.value!!.data.toString())
        } else {
            homeViewModel.loadFavoriteMovies()
        }
    }

}
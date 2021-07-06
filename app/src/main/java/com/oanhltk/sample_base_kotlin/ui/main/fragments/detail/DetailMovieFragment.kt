package com.oanhltk.sample_base_kotlin.ui.main.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.oanhltk.sample_base_kotlin.R

class DetailMovieFragment : Fragment() {

    private lateinit var favoriteViewModel: DetailMovieViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favoriteViewModel = ViewModelProvider(this).get(DetailMovieViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_detail_movie, container, false)
        return root
    }
}
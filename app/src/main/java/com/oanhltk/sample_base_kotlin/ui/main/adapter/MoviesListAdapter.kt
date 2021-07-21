package com.an.trailers.ui.main.adapter

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.databinding.MoviesListItemBinding


class MoviesListAdapter(private val fragment: Fragment) : RecyclerView.Adapter<MoviesListAdapter.CustomViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null

    private var movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListAdapter.CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = MoviesListItemBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(itemBinding)
    }

    fun setItems(movies: List<Movie>) {
        val startPosition = this.movies.size
        this.movies.addAll(movies)
        notifyItemRangeChanged(startPosition, movies.size)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun getItem(position: Int): Movie {
        return movies[position]
    }

    override fun onBindViewHolder(holder: MoviesListAdapter.CustomViewHolder, position: Int) {
        holder.bindTo(holder, getItem(position))
    }

    inner class CustomViewHolder(private val binding: MoviesListItemBinding) :  RecyclerView.ViewHolder(binding.root) {
        init {
            val displayMetrics = DisplayMetrics()
            fragment.activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            val width = displayMetrics.widthPixels

            itemView.layoutParams = LinearLayout.LayoutParams((width * 0.75f).toInt(),(width * 0.75f * 1.3).toInt())
        }

        fun bindTo(holder: MoviesListAdapter.CustomViewHolder, movie: Movie) {
            Glide.with(holder.itemView.context)
                .load(movie.getFormattedPosterPath())
                .into(binding.image)

            holder.itemView.setOnClickListener{
                onItemClick?.invoke(movie)
            }
        }
    }
}

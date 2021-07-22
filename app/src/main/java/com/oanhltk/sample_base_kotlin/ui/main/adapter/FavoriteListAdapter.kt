package com.oanhltk.sample_base_kotlin.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oanhltk.sample_base_kotlin.data.entity.Movie
import com.oanhltk.sample_base_kotlin.databinding.ListItemSavedBinding


class FavoriteListAdapter() : RecyclerView.Adapter<FavoriteListAdapter.CustomViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null

    private var movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ListItemSavedBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(itemBinding)
    }

    fun setItems(listMovie: List<Movie>) {
        movies = mutableListOf()
        val startPosition = this.movies.size
        this.movies.addAll(listMovie)
        notifyItemRangeChanged(startPosition, listMovie.size)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun getItem(position: Int): Movie {
        return movies[position]
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTo(holder, getItem(position))
    }

    inner class CustomViewHolder(private val binding: ListItemSavedBinding) :  RecyclerView.ViewHolder(binding.root) {
        init { }

        fun bindTo(holder: CustomViewHolder, movie: Movie) {
            Glide.with(holder.itemView.context)
                .load(movie.getFormattedPosterPath())
                .into(binding.imagePoster)

            binding.txtTitle.setText(movie.title)

            holder.itemView.setOnClickListener{
                onItemClick?.invoke(movie)
            }
        }
    }
}

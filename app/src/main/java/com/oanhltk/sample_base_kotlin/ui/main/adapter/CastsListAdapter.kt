package com.oanhltk.sample_base_kotlin.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oanhltk.sample_base_kotlin.data.entity.Cast
import com.oanhltk.sample_base_kotlin.databinding.CastListItemBinding


class CastsListAdapter(private val fragment: Fragment) : RecyclerView.Adapter<CastsListAdapter.CustomViewHolder>() {
    private var casts: MutableList<Cast> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = CastListItemBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(itemBinding)
    }

    fun setItems(casts: List<Cast>) {
        this.casts.addAll(casts)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return casts.size
    }

    fun getItem(position: Int): Cast{
        return casts[position]
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTo(holder, getItem(position))
    }

    inner class CustomViewHolder(private val binding: CastListItemBinding) :  RecyclerView.ViewHolder(binding.root) {
        init { }

        fun bindTo(holder: CustomViewHolder, cast: Cast) {
            Glide.with(holder.itemView.context)
                .load(cast.getFormattedProfilePath())
                .into(binding.image)

            binding.txtName.text = cast.originalName
            binding.txtCharacter.text = cast.character

        }
    }
}

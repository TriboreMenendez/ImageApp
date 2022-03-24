package com.example.imageapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imageapp.R
import com.example.imageapp.databinding.ItemRecyclerBinding
import com.example.imageapp.domain.model.ImageDomainModel

class AdapterRecycler(private val onClick: (ImageDomainModel) -> Unit) :
    ListAdapter<ImageDomainModel, AdapterRecycler.AdapterViewHolder>(DiffCallback) {

    class AdapterViewHolder(
        private val binding: ItemRecyclerBinding,
        private val onClick: (ImageDomainModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: ImageDomainModel) {
            Glide
                .with(itemView)
                .load(image.imageUrl)
                .placeholder(R.drawable.ic_load_image)
                .error(R.drawable.ic_error_image)
                .fallback(R.drawable.ic_load_image)
                .centerCrop()
                .into(binding.itemImageView)
            itemView.setOnClickListener {
                onClick(image)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ImageDomainModel>() {
        override fun areItemsTheSame(
            oldItem: ImageDomainModel,
            newItem: ImageDomainModel
        ) =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ImageDomainModel,
            newItem: ImageDomainModel
        ) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), onClick
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
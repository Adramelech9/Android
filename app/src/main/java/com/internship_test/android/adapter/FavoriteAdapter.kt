package com.internship_test.android.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.internship_test.android.R
import com.internship_test.android.databinding.FragmentFavoriteBinding
import com.internship_test.android.model.Animal
import com.squareup.picasso.Picasso

class FavoriteAdapter(private val animalList: List<Animal>) :
    ListAdapter<Animal, FavoriteAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(binding: FragmentFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(
                    FragmentFavoriteBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal) = oldItem == newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Animal, newItem: Animal) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val imageView: ImageView = holder.itemView.findViewById(R.id.iv_favorite)
        Picasso.get().load(animalList[position].url).into(imageView)
    }

}

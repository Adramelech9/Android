package com.internship_test.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.internship_test.android.R
import com.internship_test.android.fragment.FavoriteFragment
import com.internship_test.android.model.Animal
import com.squareup.picasso.Picasso

class FavoriteAdapter(private val animal: ArrayList<Animal>) :
    RecyclerView.Adapter<FavoriteAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.iv_favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_favorite, parent, false)
        return AnimalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        Picasso.get().load(animal[position].url).into(holder.imageView)
    }

    override fun getItemCount() = animal.size
}
package com.internship_test.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.internship_test.android.R
import com.internship_test.android.fragment.FavoriteFragment
import com.internship_test.android.model.Animal
import com.squareup.picasso.Picasso

class AnimalAdapter(private val animal: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        var star: ImageView = itemView.findViewById(R.id.star)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_random, parent, false)
        return AnimalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        Picasso.get().load(animal[position].url).into(holder.imageView)
        holder.imageView.setOnClickListener{
            FavoriteFragment.FAVORITE_LIST.add(animal[position])
            holder.star.isVisible = true
        }
    }

    override fun getItemCount() = animal.size
}
package com.internship_test.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.internship_test.android.R
import com.internship_test.android.model.Animal

class AnimalAdapter(private val animal: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var animalUrl: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_random_animals, parent, false)
        return AnimalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.animalUrl.setImageURI(animal[position].url.toUri())
    }

    override fun getItemCount() = animal.size
}
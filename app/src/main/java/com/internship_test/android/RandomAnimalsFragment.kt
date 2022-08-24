package com.internship_test.android

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.internship_test.android.adapter.AnimalAdapter
import com.internship_test.android.model.Animal
import java.net.URL

class RandomAnimalsFragment : Fragment(R.layout.fragment_random_animals) {

    private lateinit var rvRandomAnimals: RecyclerView
    private val animals = ArrayList<Animal>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvRandomAnimals = view.findViewById(R.id.rv_random)
        animals.add(Animal("https://upload.wikimedia.org/wikipedia/commons/b/b4/Hystrix_africaeaustralis_Blijdorp_Rotterdam.JPG"))
        rvRandomAnimals.layoutManager = LinearLayoutManager(this.context)
        rvRandomAnimals.adapter = AnimalAdapter(animals)
    }
}
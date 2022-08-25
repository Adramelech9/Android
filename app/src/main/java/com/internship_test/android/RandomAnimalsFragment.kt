package com.internship_test.android

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.internship_test.android.adapter.AnimalAdapter
import com.internship_test.android.model.Animal
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class RandomAnimalsFragment : Fragment(R.layout.fragment_random_animals) {

    private lateinit var rvRandomAnimals: RecyclerView
    private val animals = ArrayList<Animal>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        connectToUrl()
        rvRandomAnimals = view.findViewById(R.id.rv_random)
        rvRandomAnimals.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        rvRandomAnimals.adapter = AnimalAdapter(animals)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun connectToUrl() {
        Thread {
            try {
                if (animals.isEmpty()) {
                    val jsonArray = JSONArray(URL(URL).readText())
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                        animals.add(Animal(jsonObject.getString("image_link")))
                    }
                }
                Log.d("INFO", "connection ${animals.size}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    companion object {
        const val URL = "https://zoo-animal-api.herokuapp.com/animals/rand/10"
    }
}
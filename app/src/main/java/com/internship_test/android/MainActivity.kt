package com.internship_test.android

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.internship_test.android.adapter.AnimalAdapter
import com.internship_test.android.model.Animal
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var showAnimals: Button
    private lateinit var returnButton: ImageButton

    private val fRandom = RandomAnimalsFragment()

    override fun onBackPressed() {
        showAnimals.isVisible = true
        returnButton.isVisible = false
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showAnimals = findViewById(R.id.random_animal)
        returnButton = findViewById(R.id.back)

        Thread {
            try {
                var apiResponse = URL(URL).readText()

                //var result = JSONObject(apiResponse).getString("image_link")
                Log.d("INFO", apiResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()

        returnButton.setOnClickListener { onBackPressed() }

        if (savedInstanceState != null) onRestoreInstanceState(savedInstanceState)
        showAnimals.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                showAnimals.isVisible = false
                returnButton.isVisible = true
                replace(R.id.flFragment, fRandom)
                addToBackStack(null)
                commit()
            }
        }
    }

    companion object {
        const val URL = "https://zoo-animal-api.herokuapp.com/animals/rand/10"
    }
}
package com.internship_test.android

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var showAnimals: Button
    private lateinit var returnButton: ImageButton
    private lateinit var fLayout: FrameLayout

    private val fRandom = RandomAnimalsFragment()

    override fun onBackPressed() {
        showAnimals.isEnabled = true
        returnButton.isVisible = false
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showAnimals = findViewById(R.id.random_animal)
        returnButton = findViewById(R.id.back)
        fLayout = findViewById(R.id.flFragment)

        returnButton.setOnClickListener { onBackPressed() }
        showAnimals.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                showAnimals.isEnabled = false
                returnButton.isVisible = true
                replace(R.id.flFragment, fRandom)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        showAnimals.isEnabled = true
        returnButton.isVisible = false
        super.onRestoreInstanceState(savedInstanceState)
    }
}
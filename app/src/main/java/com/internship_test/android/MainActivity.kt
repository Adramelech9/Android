package com.internship_test.android

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.internship_test.android.Fragment.Companion.COUNT

class MainActivity : AppCompatActivity() {

    private lateinit var countClicks: TextView
    private lateinit var plusCount: Button

    private val fragment = Fragment()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countClicks = findViewById(R.id.countClicks)
        plusCount = findViewById(R.id.plusCount)
        countClicks.text = "Clicks on fragment: $COUNT"

        if (savedInstanceState != null) onRestoreInstanceState(savedInstanceState)
        plusCount.setOnClickListener {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("count", COUNT)
        outState.putString("countClicks", countClicks.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        COUNT = savedInstanceState.getInt("count")
        savedInstanceState.putString("countClicks", countClicks.text.toString())
        countClicks.text = savedInstanceState.getString("countClicks")
    }
}
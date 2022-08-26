package com.internship_test.android

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.internship_test.android.adapter.VPAdapter
import com.internship_test.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private val model: MainViewModel by viewModels()
    private val fragmentList = listOf(
        RandomAnimalsFragment.newInstance(),
        FavoriteFragment.newInstance()
    )

    private val titleList = listOf("random", "favorite")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = VPAdapter(this, fragmentList)
        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.vp2) { tab, pos ->
            tab.text = titleList[pos]
        }.attach()
    }
}
package com.internship_test.android.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class VPAdapter(fActivity: FragmentActivity, private val list: List<Fragment>) :
    FragmentStateAdapter(fActivity) {
    override fun getItemCount() = list.size

    override fun createFragment(position: Int): Fragment = list[position]
}
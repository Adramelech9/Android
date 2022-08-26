package com.internship_test.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.internship_test.android.adapter.FavoriteAdapter
import com.internship_test.android.databinding.FragmentFavoriteBinding
import com.internship_test.android.model.Animal

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.runOnUiThread {
            binding.rvFavorite.layoutManager = LinearLayoutManager(
                this.context, LinearLayoutManager.HORIZONTAL, false
            )
            binding.rvFavorite.adapter = FavoriteAdapter(FAVORITE_LIST)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = FavoriteFragment()
        var FAVORITE_LIST = arrayListOf<Animal>()

    }
}
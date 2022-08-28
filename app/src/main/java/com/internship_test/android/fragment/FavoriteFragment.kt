package com.internship_test.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.internship_test.android.adapter.FavoriteAdapter
import com.internship_test.android.databinding.FragmentFavoriteBinding
import com.internship_test.android.model.Animal

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = FirebaseDatabase.getInstance(DATABASE_URL)
        val favRef = database.getReference("favorite")
        val listAnimal = ArrayList<Animal>()
        onChangeListener(favRef, listAnimal)
        initRV(listAnimal)
    }

    private fun onChangeListener(favRef: DatabaseReference, listAnimal: ArrayList<Animal>) {
        favRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (s in snapshot.children) {
                    val animal = s.getValue(Animal::class.java)
                    if (animal != null) listAnimal.add(animal)
                }
                adapter.submitList(listAnimal)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun initRV(listAnimal: ArrayList<Animal>) = with(binding) {
        adapter = FavoriteAdapter(listAnimal)
        rvFavorite.layoutManager = LinearLayoutManager(
            this@FavoriteFragment.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rvFavorite.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() = FavoriteFragment()
        private const val DATABASE_URL =
            "https://android-22a2b-default-rtdb.europe-west1.firebasedatabase.app"
    }
}
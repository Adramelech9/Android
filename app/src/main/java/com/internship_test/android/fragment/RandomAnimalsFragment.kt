package com.internship_test.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.internship_test.android.adapter.AnimalAdapter
import com.internship_test.android.databinding.FragmentRandomBinding
import com.internship_test.android.model.Animal
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class RandomAnimalsFragment : Fragment() {
    private lateinit var binding: FragmentRandomBinding
    private val animals = ArrayList<Animal>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectToUrl()
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
                rvBuild()
            } catch (e: Exception) {
                e.printStackTrace()
                connectToUrl()
            }
        }.start()
    }

    private fun rvBuild() {
        activity?.runOnUiThread {
            binding.rvRandom.layoutManager = LinearLayoutManager(
                this.context, LinearLayoutManager.HORIZONTAL, false
            )
            binding.rvRandom.adapter = AnimalAdapter(animals)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RandomAnimalsFragment()
        const val URL = "https://zoo-animal-api.herokuapp.com/animals/rand/10"
    }
}
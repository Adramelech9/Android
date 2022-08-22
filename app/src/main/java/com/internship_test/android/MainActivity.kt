package com.internship_test.android

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.internship_test.android.adapter.UserAdapter
import com.internship_test.android.user.User
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), UserAdapter.Listener {

    private lateinit var age: EditText
    private var pref: SharedPreferences? = null
    private var userList = ArrayList<User>()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        age = findViewById(R.id.input_age)
        age.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    age.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                },
                year, month, day
            ).show()
        }

        pref = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val connectionsJSONString = pref?.getString("user", null)
        val type: Type = object : TypeToken<ArrayList<User>>() {}.type
        userList = if (connectionsJSONString == null) ArrayList()
        else Gson().fromJson(connectionsJSONString, type)

        buildRecycler()
    }

    override fun onClick(user: User) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView2, Fragment())
            addToBackStack(null)
            commit()
        }
    }

    fun addUser(view: View) {
        var name = findViewById<EditText>(R.id.input_name).text.toString()

        userList.add(User(name, age.text.toString()))
        Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
        buildRecycler()
        val json = Gson().toJson(userList)
        pref?.edit()?.putString("user", json)?.apply()
        Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
    }

    private fun buildRecycler() {
        val recyclerView: RecyclerView = findViewById(R.id.userRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserAdapter(this, userList)
    }
}
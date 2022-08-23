package com.internship_test.android

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.internship_test.android.adapter.UserAdapter
import com.internship_test.android.user.User
import java.lang.reflect.Type


class MainActivity : AppCompatActivity(), UserAdapter.Listener {

    private lateinit var addUserPage: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var sortByName: TextView
    private lateinit var sortByAge: TextView
    private lateinit var sortByIsStudent: TextView
    private var pref: SharedPreferences? = null
    private var userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sortByName = findViewById(R.id.sort_by_name)
        sortByAge = findViewById(R.id.sort_by_age)
        sortByIsStudent = findViewById(R.id.sort_by_is_student)
        sortByName.setOnClickListener {
            userList.sortBy { it.name }
            savePref()
        }
        sortByAge.setOnClickListener {
            userList.sortBy { it.age }
            savePref()
        }
        sortByIsStudent.setOnClickListener {
            userList.sortBy { it.isStudent }
            savePref()
        }
        pref = getSharedPreferences("USER", Context.MODE_PRIVATE)
        addUserPage = findViewById(R.id.add_user_page)
        addUserPage.setOnClickListener {
            startActivity(Intent(this, AddUserActivity::class.java))
        }
        buildRecycler()
    }

    override fun onResume() {
        super.onResume()
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

    //delete user
    override fun onLongClick(user: User) {
        AlertDialog.Builder(this)
            .setMessage("Delete user ${user.name}?")
            .setPositiveButton("Yes") { _, _ ->
                userList.remove(user)
                savePref()
            }.setNeutralButton("No", null).show()
    }

    override fun saveCheckBox(user: User) {
        userList.find { it.name == user.name }?.isStudent = user.isStudent
        savePref(false)
    }

    private fun buildRecycler() {
        recyclerView = findViewById(R.id.userRecycler)
        val jsonString = pref?.getString("user", null)
        val type: Type = object : TypeToken<ArrayList<User>>() {}.type
        userList = if (jsonString == null) ArrayList()
        else Gson().fromJson(jsonString, type)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserAdapter(this, userList)
    }

    private fun savePref(isSort: Boolean = true) {
        pref?.edit()?.putString("user", Gson().toJson(userList))?.apply()
        if (isSort) recyclerView.adapter?.notifyDataSetChanged()
    }
}
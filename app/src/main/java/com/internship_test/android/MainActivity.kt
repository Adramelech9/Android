package com.internship_test.android

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
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
    private var pref: SharedPreferences? = null
    private var userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

/*    override fun saveCheckBox() {
        userList.find { it.name == UserAdapter.USER_NAME }
            ?.isStudent = UserAdapter.USER_IS_STUDENT
        buildRecycler(true)
    }*/

    private fun buildRecycler(changeCheckBox: Boolean = false) {
        pref = getSharedPreferences("USER", Context.MODE_PRIVATE)
        val jsonString = pref?.getString("user", null)
        val type: Type = object : TypeToken<ArrayList<User>>() {}.type
        if (!changeCheckBox)
        userList = if (jsonString == null) ArrayList()
        else Gson().fromJson(jsonString, type)
        val recyclerView: RecyclerView = findViewById(R.id.userRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserAdapter(this, userList)
    }
}
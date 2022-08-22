package com.internship_test.android

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.internship_test.android.adapter.UserAdapter
import com.internship_test.android.user.User

class MainActivity : AppCompatActivity(), UserAdapter.Listener {

    var pref: SharedPreferences? = null
    var userList = ArrayList<User>()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(userList) {
            add(User("Smith", 18))
            add(User("Tom", 19))
            add(User("Bond", 21))
            add(User("Andre", 20))
            add(User("Sasha", 17))
            add(User("Kim", 30))
            add(User("Kate", 32))
        }
        buildRecycler()
    }

    override fun onClick(user: User) {
        var fragment = Fragment()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().apply {
            userList.add(User("Kamilla", 22))
            replace(R.id.fragmentContainerView2, fragment)
            addToBackStack(null)
            commit()
        }
    }

    fun addUser(view: View) {
        var name = findViewById<EditText>(R.id.input_name).text.toString()
        var age = findViewById<EditText>(R.id.input_age).text.toString().toInt()
        userList.add(User(name, age))
        Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
        buildRecycler()
    }

    private fun buildRecycler() {
        val recyclerView: RecyclerView = findViewById(R.id.userRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserAdapter(this, userList)
    }
}
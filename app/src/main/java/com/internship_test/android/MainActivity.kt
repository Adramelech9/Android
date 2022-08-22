package com.internship_test.android

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.internship_test.android.adapter.UserAdapter
import com.internship_test.android.user.User

class MainActivity : AppCompatActivity(), UserAdapter.Listener {
    var fragment = Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userList = ArrayList<User>()
        with(userList) {
            add(User("Smith", 18))
            add(User("Tom", 19))
            add(User("Bond", 21))
            add(User("Andre", 20))
            add(User("Sasha", 17))
            add(User("Kim", 30))
            add(User("Kate", 32))
        }
        val recyclerView: RecyclerView = findViewById(R.id.userRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserAdapter(this, userList)

        /*{
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2, fragment)
                addToBackStack(null)
                commit()
            }
        }*/

        /*var fUserName: TextView = findViewById(R.id.fUserName)
        var fUserAge: TextView = findViewById(R.id.fUserAge)
        var fUserIsStudent: TextView = findViewById(R.id.fUserIsStudent)

        fUserName.text = NAME
        fUserAge.text = AGE
        fUserIsStudent.text = IS_STUDENT*/
        if (savedInstanceState != null) onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onClick(user: User) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView2, fragment)
            addToBackStack(null)
            commit()
        }
        //Toast.makeText(this, "${user.age}", Toast.LENGTH_LONG).show()
    }
}
package com.internship_test.android

import android.app.DatePickerDialog
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.internship_test.android.user.User
import java.lang.reflect.Type
import java.util.*

class AddUserActivity : AppCompatActivity() {
    private lateinit var age: EditText
    private var userList = ArrayList<User>()
    private var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        val returnButton = findViewById<ImageButton>(R.id.return_back)
        returnButton.setOnClickListener {
            finish()
        }

        age = findViewById(R.id.input_age2)
        age.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    val day = if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth
                    age.setText("$day-${monthOfYear + 1}-$year")
                },
                year, month, day
            ).show()
        }
    }

    fun addUser(view: View) {
        val name = findViewById<EditText>(R.id.input_name2).text.toString()

        pref = getSharedPreferences("USER", MODE_PRIVATE)
        val jsonString = pref?.getString("user", null)
        val type: Type = object : TypeToken<ArrayList<User>>() {}.type
        userList = if (jsonString == null) ArrayList()
        else Gson().fromJson(jsonString, type)
        userList.add(User(name, age.text.toString()))
        val json = Gson().toJson(userList)
        pref?.edit()?.putString("user", json)?.apply()
        Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()

        onBackPressed()
    }
}
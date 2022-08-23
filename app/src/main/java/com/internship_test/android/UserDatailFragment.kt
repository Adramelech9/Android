package com.internship_test.android

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.internship_test.android.user.User

class UserDetailFragment(var user: User) : Fragment(R.layout.fragment_user_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fUserName: TextView = view.findViewById(R.id.fUserName)
        val fUserAge: TextView = view.findViewById(R.id.fUserAge)
        val fUserIsStudent: TextView = view.findViewById(R.id.fUserIsStudent)

        fUserName.text = user.name
        fUserAge.text = user.age
        fUserIsStudent.text = user.isStudent.toString()

        super.onViewCreated(view, savedInstanceState)
    }
}

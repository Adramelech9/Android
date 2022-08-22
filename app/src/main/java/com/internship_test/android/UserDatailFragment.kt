package com.internship_test.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class UserDetailFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var fUserName: TextView = view.findViewById(R.id.fUserName)
        var fUserAge: TextView = view.findViewById(R.id.fUserAge)
        var fUserIsStudent: TextView = view.findViewById(R.id.fUserIsStudent)

        super.onViewCreated(view, savedInstanceState)
    }
}
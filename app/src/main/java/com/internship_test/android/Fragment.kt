package com.internship_test.android

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class Fragment : Fragment(R.layout.fragment) {

    var count = COUNT
    private lateinit var fButton: Button
    private lateinit var fTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fButton = view.findViewById(R.id.fragment_button)
        fTextView = view.findViewById(R.id.fragment_textView)
        fTextView.text = "Count of clicks: $count"
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) onViewStateRestored(savedInstanceState)

        fButton.setOnClickListener {
            count++
            fTextView.text = "Count of clicks: $count"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        COUNT = count
        super.onSaveInstanceState(outState)
    }

    companion object {
        var COUNT = 0
    }
}
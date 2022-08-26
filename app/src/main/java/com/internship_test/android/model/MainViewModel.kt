package com.internship_test.android.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.internship_test.android.model.Animal

class MainViewModel: ViewModel() {
    val animal = MutableLiveData<Animal>()
}
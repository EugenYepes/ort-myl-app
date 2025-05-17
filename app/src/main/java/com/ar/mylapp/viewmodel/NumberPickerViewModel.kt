package com.ar.mylapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NumberPickerViewModel : ViewModel() {

    var number by mutableIntStateOf(0)
        private set

    fun increment(max: Int) {
        if (number < max) number++
    }

    fun decrement(min: Int) {
        if (number > min) number--
    }
}
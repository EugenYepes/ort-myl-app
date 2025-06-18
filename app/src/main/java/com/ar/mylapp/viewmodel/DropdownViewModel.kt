package com.ar.mylapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class DropdownViewModel : ViewModel() {

    private val _selectedOptionsMap = mutableStateMapOf<String, SnapshotStateList<String>>()
    val selectedOptionsMap: Map<String, SnapshotStateList<String>> get() = _selectedOptionsMap

    fun getSelectedOptions(key: String): SnapshotStateList<String> {
        return _selectedOptionsMap.getOrPut(key) { mutableStateListOf() }
    }

    fun onOptionToggled(key: String, option: String, isChecked: Boolean) {
        val list = getSelectedOptions(key)
        if (isChecked) list.add(option) else list.remove(option)
    }
}
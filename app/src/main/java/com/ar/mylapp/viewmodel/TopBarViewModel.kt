package com.ar.mylapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TopBarViewModel: ViewModel() {

    private val _title = mutableStateOf("Inicio")
    val title: State<String> = _title

    private val _subtitle = mutableStateOf<String?>(null)
    val subtitle: State<String?> = _subtitle

    fun setTopBar(title: String, subtitle: String? = null) {
        _title.value = title
        _subtitle.value = subtitle
    }
}
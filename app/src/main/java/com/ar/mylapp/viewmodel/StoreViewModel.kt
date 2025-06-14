package com.ar.mylapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.myldtos.users.StoreDTO
import com.ar.mylapp.repository.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val repository: StoreRepository
) : ViewModel() {

    private val _stores = MutableStateFlow<List<StoreDTO>>(emptyList())
    val stores: StateFlow<List<StoreDTO>> = _stores

    fun loadStores() {
        viewModelScope.launch {
            _stores.value = repository.fetchValidStores()
        }
    }
}
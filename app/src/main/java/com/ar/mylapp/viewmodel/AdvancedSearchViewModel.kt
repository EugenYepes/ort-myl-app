package com.ar.mylapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.mylapp.models.cardProperties.CardPropertiesDTO
import com.ar.mylapp.repository.GetServiceCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdvancedSearchViewModel @Inject constructor(
    private val repository: GetServiceCardRepository
) : ViewModel() {

    var dropdownOptions by mutableStateOf<Map<String, List<CardPropertiesDTO>>>(emptyMap())
        private set

    var selectedOptions = mutableStateMapOf<String, List<CardPropertiesDTO>>()
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun loadOptions() {
        viewModelScope.launch {
            isLoading = true
            try {
                dropdownOptions = repository.getFilterOptions()
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun toggleOption(label: String, option: CardPropertiesDTO, selected: Boolean) {
        val current = selectedOptions[label] ?: emptyList()
        selectedOptions[label] = if (selected) current + option else current - option
    }
}
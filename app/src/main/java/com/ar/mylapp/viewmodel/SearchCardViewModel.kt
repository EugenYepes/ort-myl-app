package com.ar.mylapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.repository.GetServiceCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCardViewModel @Inject constructor(
    private val repository: GetServiceCardRepository
) : ViewModel() {

    var searchQuery by mutableStateOf("")

    var foundCards by mutableStateOf<List<CardDTO>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun updateQuery(newQuery: String) {
        searchQuery = newQuery

        if (newQuery.isBlank()) {
            foundCards = emptyList()
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val result = repository.searchCardsByName(newQuery)
                foundCards = result ?: emptyList()
            } catch (e: Exception) {
                errorMessage = "Error al buscar cartas: ${e.localizedMessage ?: "desconocido"}"
                foundCards = emptyList()
            } finally {
                isLoading = false
            }
        }
    }

}




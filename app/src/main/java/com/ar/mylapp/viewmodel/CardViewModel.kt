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
class CardViewModel @Inject constructor(
    private val cardRepository: GetServiceCardRepository
) : ViewModel() {

    var cards by mutableStateOf<List<CardDTO>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        getCards()
    }

    /*private fun getCards() {
        viewModelScope.launch {
            try {
                isLoading = true
                cards = cardRepository.fetchCards() ?: emptyList()
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }*/
    private fun getCards() {
        viewModelScope.launch {
            isLoading = true
            try {
                val result = cardRepository.fetchCards()
                if (result != null) {
                    cards = result
                    errorMessage = null
                } else {
                    errorMessage = "Error: respuesta vacía del servidor"
                }
            } catch (e: Exception) {
                errorMessage = "Error de red: ${e.localizedMessage ?: "desconocido"}"
            } finally {
                isLoading = false
            }
        }
    }
}
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

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private var currentPage = 1
    private val pageSize = 15
    private var endReached = false

    init {
        loadMoreCards()
    }

    fun loadMoreCards() {
        if (isLoading || endReached) return

        viewModelScope.launch {
            isLoading = true
            try {
                val result = cardRepository.fetchCards(currentPage, pageSize)
                if (result.isNullOrEmpty()) {
                    endReached = true
                } else {
                    cards = cards + result
                    currentPage++
                    errorMessage = null
                }
            } catch (e: Exception) {
                errorMessage = "Error de red: ${e.localizedMessage ?: "desconocido"}"
            } finally {
                isLoading = false
            }
        }
    }
}

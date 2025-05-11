package com.ar.mylapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.mylapp.models.Card
import com.ar.mylapp.repository.GetServiceCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardRepository: GetServiceCardRepository
) : ViewModel() {

    var cards by mutableStateOf<List<Card>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        getCards()
    }

    private fun getCards() {
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
    }
}
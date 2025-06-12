package com.ar.mylapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.myldtos.users.DeckDTO
import com.ar.mylapp.repository.DeckRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DecksViewModel @Inject constructor(
    private val repository: DeckRepository
) : ViewModel() {

    var decks by mutableStateOf(listOf<DeckDTO>())
        private set

    fun loadDecks(token: String) {
        viewModelScope.launch {
            try {
                decks = repository.getDecks("Bearer $token")
            } catch (e: Exception) {
                Log.e("DecksViewModel", "Error cargando decks: ${e.message}")
            }
        }
    }

    fun addDeck(token: String, name: String, description: String) {
        if (name.isNotBlank()) {
            viewModelScope.launch {
                try {
                    repository.addDeck("Bearer $token", name, description)
                    loadDecks("Bearer $token")
                } catch (e: Exception) {
                    Log.e("DecksViewModel", "Error creando deck: ${e.message}")
                }
            }
        }
    }
}
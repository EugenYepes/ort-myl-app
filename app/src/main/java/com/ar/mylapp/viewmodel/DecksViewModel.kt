package com.ar.mylapp.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.myldtos.users.DeckDTO
import com.ar.mylapp.models.cardProperties.DeckCardProperties
import com.ar.mylapp.repository.DeckRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel
class DecksViewModel @Inject constructor(
    private val repository: DeckRepository
) : ViewModel() {

    var selectedCardId: Int? by mutableStateOf(null)

    private val _decks = mutableStateOf(listOf<DeckDTO>())
    val decks: State<List<DeckDTO>> get() = _decks

    fun loadDecks(token: String) {
        viewModelScope.launch {
            try {
                val result = repository.getDecks("Bearer $token")
                _decks.value = result
            } catch (e: Exception) {
                _decks.value = listOf<DeckDTO>()
                Log.e("DecksViewModel", "Error cargando decks: ${e.message}")
            }
        }
    }

    fun addDeck(token: String, name: String, description: String, onResult: () -> Unit = {}) {
        if (name.isNotBlank()) {
            viewModelScope.launch {
                try {
                    repository.addDeck("Bearer $token", name, description)
                    loadDecks(token)
                    onResult()
                } catch (e: Exception) {
                    Log.e("DecksViewModel", "Error creando deck: ${e.message}")
                }
            }
        }
    }

    fun editDeck(token: String, id: Int, name: String, description: String) {
        if (name.isNotBlank()) {
            val deckToEdit = _decks.value.find { it.id == id } ?: return

            val updatedDeck = DeckDTO().apply {
                this.id = deckToEdit.id
                this.name = name
                this.description = description
                this.cards = deckToEdit.cards
            }

            viewModelScope.launch {
                val success = repository.updateDeck(
                    id = updatedDeck.id,
                    name = updatedDeck.name,
                    description = updatedDeck.description,
                    token = "Bearer $token"
                )

                if (success) {
                    _decks.value = _decks.value.map { if (it.id == id) updatedDeck else it }
                } else {
                    Log.e("DecksViewModel", "Error: no se pudo editar el mazo")
                }
            }
        }
    }

    fun deleteDeck(token: String, id: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.deleteDeck("Bearer $token", id)
            if (success) {
                _decks.value = _decks.value.filter { it.id != id }
            } else {
                Log.e("DecksViewModel", "Error: no se pudo eliminar el mazo")
            }
            onResult(success)
        }
    }

    fun addCardToDecks(
        token: String,
        cardId: Int,
        deckList: List<DeckCardProperties>,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val success = repository.addCardToDeck("Bearer $token", cardId, deckList)
                if (success) {
                    loadDecks(token)
                }
                onResult(success)
            } catch (e: Exception) {
                Log.e("DecksViewModel", "Error al agregar carta a mazos: ${e.message}")
                onResult(false)
            }
        }
    }

    fun deleteCardFromDeck(
        token: String,
        cardId: Int,
        deckList: List<DeckCardProperties>,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val success = repository.deleteCardFromDeck("Bearer $token", cardId, deckList)
                if (success) {
                    loadDecks(token)
                }
                onResult(success)
            } catch (e: Exception) {
                Log.e("DecksViewModel", "Error al eliminar carta del mazo: ${e.message}")
                onResult(false)
            }
        }
    }

    fun getCardQuantitiesForCard(cardId: Int): Map<Int, Int> {
        return decks.value.associate { deck ->
            val quantity = deck.cards
                ?.firstOrNull { it.card.id == cardId }
                ?.quantity ?: 0
            deck.id to quantity
        }
    }
}
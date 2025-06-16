package com.ar.mylapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.models.cardProperties.CardId
import com.ar.mylapp.models.cardProperties.PlayerCardProperties
import com.ar.mylapp.models.cardProperties.PlayerCardRequest
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

    var selectedCard by mutableStateOf<CardDTO?>(null)
        private set

    var isCardDetailLoading by mutableStateOf(false)
        private set

    var cardDetailError by mutableStateOf<String?>(null)
        private set

    var filteredCards by mutableStateOf<List<CardDTO>>(emptyList())
    private var filters: Map<String, List<Int>> = emptyMap()

    private var currentPage = 1
    private val pageSize = 15
    private var endReached = false

    init {
        loadMoreCards()
    }

    init {
        loadMoreFilteredCards()
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

    fun loadCardById(id: Int) {
        viewModelScope.launch {
            isCardDetailLoading = true
            cardDetailError = null
            try {
                selectedCard = cardRepository.fetchCardById(id)
            } catch (e: Exception) {
                cardDetailError = "No se pudo cargar la carta: ${e.localizedMessage}"
            } finally {
                isCardDetailLoading = false
            }
        }
    }

    fun loadFilteredCards(newFilters: Map<String, List<Int>>) {
        filters = newFilters
        currentPage = 1
        endReached = false
        filteredCards = emptyList()

        loadMoreFilteredCards()
    }

    fun loadMoreFilteredCards() {
        if (isLoading || endReached) return
        viewModelScope.launch {
            isLoading = true
            try {
                val result = cardRepository.fetchFilteredCards(currentPage, pageSize, filters)

                if (result.isEmpty()) {
                    endReached = true
                } else {
                    filteredCards += result
                    currentPage++
                }
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    var carouselCards by mutableStateOf<List<CardDTO>>(emptyList())
        private set
    fun loadRandomCardsForCarousel(maxPages: Int = 1000, count: Int = 10) {
        viewModelScope.launch {
            isLoading = true
            try {
                val randomPage = (1..maxPages).random()
                val result = cardRepository.fetchCards(randomPage, pageSize = 15)
                if (!result.isNullOrEmpty()) {
                    carouselCards = result.shuffled().take(count)
                } else {
                    errorMessage = "No se encontraron cartas para el carrusel"
                }
            } catch (e: Exception) {
                errorMessage = "Error cargando cartas para carrusel: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }

    private var currentUserCardsPage = 1
    private val userCardsPageSize = 15
    private var userCardsEndReached = false
    var userCards by mutableStateOf<List<PlayerCardProperties>>(emptyList())
        private set

    fun loadMoreUserCards(token: String) {
        if (isLoading || userCardsEndReached) return

        viewModelScope.launch {
            isLoading = true
            try {
                val result = cardRepository.getUserCards("Bearer $token", currentUserCardsPage, userCardsPageSize)
                if (result.isEmpty()) {
                    userCardsEndReached = true
                } else {
                    userCards = userCards + result
                    currentUserCardsPage++
                    errorMessage = null
                }
            } catch (e: Exception) {
                errorMessage = "Error al obtener cartas obtenidas: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun resetAndLoadUserCards(token: String) {
        userCards = emptyList()
        currentUserCardsPage = 1
        userCardsEndReached = false
        loadMoreUserCards(token)
    }

    fun addCardToUserCards(token: String, cardId: Int, quantity: Int) {
        viewModelScope.launch {
            try {
                val success = cardRepository.addPlayerCards(
                    "Bearer $token",
                    listOf(PlayerCardRequest(CardId(cardId), quantity))
                )
                if (success) loadMoreUserCards(token) else errorMessage = "Error al agregar carta"
            } catch (e: Exception) {
                errorMessage = "Error al agregar carta: ${e.message}"
            }
        }
    }

    fun removeCardFromUserCards(token: String, cardId: Int, quantity: Int) {
        viewModelScope.launch {
            try {
                val success = cardRepository.deletePlayerCards(
                    "Bearer $token",
                    listOf(PlayerCardRequest(CardId(cardId), quantity))
                )
                if (success) loadMoreUserCards(token) else errorMessage = "Error al eliminar carta"
            } catch (e: Exception) {
                errorMessage = "Error al eliminar carta: ${e.message}"
            }
        }
    }

    fun getQuantityOfObtainedCard(cardId: Int): Int {
        return userCards.firstOrNull { it.card.id == cardId }?.quantity ?: 0
    }
}

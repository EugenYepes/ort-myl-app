package com.ar.mylapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ar.com.myldtos.cards.CardDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchCardViewModel @Inject constructor() : ViewModel() {

    var searchQuery by mutableStateOf("")

    var foundCards by mutableStateOf<List<CardDTO>>(emptyList())
        private set

    fun updateQuery(newQuery: String, allCards: List<CardDTO>) {
        searchQuery = newQuery
        foundCards = if (newQuery.isBlank()) {
            emptyList()
        } else {
            allCards.filter {
                it.name.contains(newQuery, ignoreCase = true)
            }
        }
    }

    fun clearSearch() {
        searchQuery = ""
        foundCards = emptyList()
    }

}




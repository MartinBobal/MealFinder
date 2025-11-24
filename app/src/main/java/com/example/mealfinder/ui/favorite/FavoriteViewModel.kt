package com.example.mealfinder.ui.favorite

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoriteViewModel : ViewModel() {

    private val _favorites = MutableStateFlow<List<String>>(emptyList())
    val favorites: StateFlow<List<String>> = _favorites

    fun addFavorite(item: String) {
        _favorites.value = _favorites.value + item
    }

    fun removeFavorite(item: String) {
        _favorites.value = _favorites.value - item
    }
}

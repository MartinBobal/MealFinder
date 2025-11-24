package com.example.mealfinder.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class FavoriteViewModel : ViewModel() {

    private val _favorites = MutableStateFlow<Map<String, String>>(emptyMap())
    val favorites: StateFlow<Map<String, String>> = _favorites.asStateFlow()

    fun addFavorite(id: String, name: String) {
        _favorites.value = _favorites.value + (id to name)
    }

    fun removeFavorite(id: String) {
        _favorites.value = _favorites.value - id
    }

    fun isFavorite(id: String): StateFlow<Boolean> {
        return _favorites
            .map { it.containsKey(id) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = _favorites.value.containsKey(id)
            )
    }
}

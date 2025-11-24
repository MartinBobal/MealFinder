package com.example.mealfinder.ui.favorite

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted

data class FavoriteItem(val id: String, val name: String)

class FavoriteViewModel : ViewModel() {

    private val _favorites = MutableStateFlow<List<FavoriteItem>>(emptyList())
    val favorites: StateFlow<List<FavoriteItem>> = _favorites

    fun addFavorite(id: String, name: String) {
        if (_favorites.value.any { it.id == id }) return
        _favorites.value = _favorites.value + FavoriteItem(id, name)
    }

    fun removeFavorite(id: String) {
        _favorites.value = _favorites.value.filterNot { it.id == id }
    }

    fun isFavorite(id: String): StateFlow<Boolean> =
        _favorites
            .map { list -> list.any { it.id == id } }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = false
            )
}

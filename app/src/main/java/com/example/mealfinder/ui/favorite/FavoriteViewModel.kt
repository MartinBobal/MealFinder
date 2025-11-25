package com.example.mealfinder.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealfinder.data.DataStoreManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = DataStoreManager(application)

    private val _favorites = MutableStateFlow<Map<String, String>>(emptyMap())
    val favorites: StateFlow<Map<String, String>> = _favorites.asStateFlow()

    init {
        viewModelScope.launch {
            dataStore.favoritesFlow.collect {
                _favorites.value = it
            }
        }
    }

    fun addFavorite(id: String, name: String) {
        val updated = _favorites.value + (id to name)
        _favorites.value = updated

        viewModelScope.launch {
            dataStore.saveFavorites(updated)
        }
    }

    fun removeFavorite(id: String) {
        val updated = _favorites.value - id
        _favorites.value = updated

        viewModelScope.launch {
            dataStore.saveFavorites(updated)
        }
    }

    fun isFavorite(id: String): StateFlow<Boolean> {
        return favorites
            .map { it.containsKey(id) }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                initialValue = _favorites.value.containsKey(id)
            )
    }
}

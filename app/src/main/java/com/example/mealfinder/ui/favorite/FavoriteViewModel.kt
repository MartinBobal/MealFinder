package com.example.mealfinder.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealfinder.data.DataStoreManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    // Správce ukládání oblíbených receptů do DataStore
    private val dataStore = DataStoreManager(application)

    // Interní stav oblíbených receptů
    private val _favorites = MutableStateFlow<Map<String, String>>(emptyMap())
    val favorites: StateFlow<Map<String, String>> = _favorites.asStateFlow()

    init {
        // Načítání uložených oblíbených receptů při startu ViewModelu
        viewModelScope.launch {
            dataStore.favoritesFlow.collect {
                _favorites.value = it
            }
        }
    }

    // Přidání receptu do oblíbených a uložení do DataStore
    fun addFavorite(id: String, name: String) {
        val updated = _favorites.value + (id to name)
        _favorites.value = updated

        viewModelScope.launch {
            dataStore.saveFavorites(updated)
        }
    }

    // Odebrání receptu z oblíbených a aktualizace DataStore
    fun removeFavorite(id: String) {
        val updated = _favorites.value - id
        _favorites.value = updated

        viewModelScope.launch {
            dataStore.saveFavorites(updated)
        }
    }

    // Flow informující, zda je konkrétní recept v oblíbených
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

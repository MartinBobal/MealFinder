package com.example.mealfinder.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.map

// Rozšíření Contextu o DataStore pro ukládání oblíbených receptů
val Context.dataStore by preferencesDataStore("favorites_store")

class DataStoreManager(private val context: Context) {

    // Klíč pro uložení oblíbených receptů ve formátu JSON
    private val FAVORITES_KEY: Preferences.Key<String> =
        stringPreferencesKey("favorites")

    // Uložení mapy oblíbených receptů do DataStore
    suspend fun saveFavorites(map: Map<String, String>) {
        val json = Gson().toJson(map)
        context.dataStore.edit { prefs ->
            prefs[FAVORITES_KEY] = json
        }
    }

    // Flow vracející mapu oblíbených receptů načtenou z DataStore
    val favoritesFlow = context.dataStore.data.map { prefs ->
        val json = prefs[FAVORITES_KEY] ?: "{}"
        Gson().fromJson(json, Map::class.java)
            .map { it.key as String to it.value as String }
            .toMap()
    }
}

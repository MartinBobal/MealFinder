package com.example.mealfinder.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealfinder.data.model.Meal
import com.example.mealfinder.data.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.mealfinder.ui.search.SearchViewModel

class SearchViewModel(
    private val repository: MealRepository
) : ViewModel() {

    // Interní stav seznamu nalezených receptů
    private val _results = MutableStateFlow<List<Meal>>(emptyList())

    // Veřejně dostupný neměnný StateFlow s výsledky
    val results = _results.asStateFlow()

    // Vyhledání receptů podle zadaného dotazu
    fun search(query: String) {
        viewModelScope.launch {
            val response = repository.searchMeals(query)
            _results.value = response.meals ?: emptyList()
        }
    }
}

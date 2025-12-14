package com.example.mealfinder.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mealfinder.data.repository.MealRepository

class SearchViewModelFactory(
    private val repository: MealRepository
) : ViewModelProvider.Factory {

    // Vytvoření instance SearchViewModelu s předaným repository
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

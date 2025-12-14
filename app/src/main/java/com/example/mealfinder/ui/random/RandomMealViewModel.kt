package com.example.mealfinder.ui.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealfinder.data.model.Meal
import com.example.mealfinder.data.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RandomMealViewModel(
    private val repository: MealRepository
) : ViewModel() {

    // Stav náhodně načteného receptu
    private val _meal = MutableStateFlow<Meal?>(null)
    val meal = _meal.asStateFlow()

    // Načtení náhodného receptu z API
    fun loadRandomMeal() {
        viewModelScope.launch {
            try {
                val response = repository.getRandomMeal()
                _meal.value = response.meals?.firstOrNull()
            } catch (e: Exception) {
                _meal.value = null
            }
        }
    }
}

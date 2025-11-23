package com.example.mealfinder.ui.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mealfinder.data.repository.MealRepository

class RandomMealViewModelFactory(
    private val repository: MealRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomMealViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RandomMealViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

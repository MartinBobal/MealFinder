package com.example.mealfinder.data.repository

import com.example.mealfinder.data.api.MealApiService

class MealRepository(
    private val api: MealApiService
) {
    suspend fun getRandomMeal() = api.getRandomMeal()
    suspend fun searchMeals(query: String) = api.searchMeals(query)
    suspend fun getMealById(id: String) = api.getMealById(id)

}

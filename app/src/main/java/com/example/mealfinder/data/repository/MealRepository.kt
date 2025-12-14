package com.example.mealfinder.data.repository

import com.example.mealfinder.data.api.MealApiService

class MealRepository(
    private val api: MealApiService
) {
    // Zprostředkování volání API pro náhodný recept
    suspend fun getRandomMeal() = api.getRandomMeal()

    // Zprostředkování vyhledávání receptů
    suspend fun searchMeals(query: String) = api.searchMeals(query)

    // Zprostředkování načtení detailu receptu podle ID
    suspend fun getMealById(id: String) = api.getMealById(id)
}

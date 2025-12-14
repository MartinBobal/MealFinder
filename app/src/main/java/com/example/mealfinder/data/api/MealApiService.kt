package com.example.mealfinder.data.api

import com.example.mealfinder.data.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    // Načtení náhodného receptu
    @GET("random.php")
    suspend fun getRandomMeal(): MealResponse

    // Vyhledání receptů podle názvu
    @GET("search.php")
    suspend fun searchMeals(
        @Query("s") query: String
    ): MealResponse

    // Načtení detailu receptu podle ID
    @GET("lookup.php")
    suspend fun getMealById(
        @Query("i") id: String
    ): MealResponse
}

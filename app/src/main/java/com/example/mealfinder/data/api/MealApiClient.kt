package com.example.mealfinder.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MealApiClient {

    // Základní URL pro TheMealDB API
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    // Konfigurace Moshi pro práci s Kotlin datovými třídami
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Lazy inicializace Retrofit klienta a API rozhraní
    val api: MealApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MealApiService::class.java)
    }
}

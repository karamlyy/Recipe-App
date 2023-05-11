package com.karamlyy.recipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.edamam.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(RecipeService::class.java)

    suspend fun fetchRecipe(query: String): Recipe? {
        val result = service.searchRecipes(query, "APP ID", "API KEY")
        return result.hits.firstOrNull()?.recipe
    }
}

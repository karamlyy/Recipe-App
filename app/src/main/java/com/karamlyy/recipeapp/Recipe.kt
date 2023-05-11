package com.karamlyy.recipeapp

data class RecipeSearchResult(
    val hits: List<Hit>
)

data class Hit(
    val recipe: Recipe
)

data class Recipe(
    val label: String,
    val ingredientLines: List<String>,
)


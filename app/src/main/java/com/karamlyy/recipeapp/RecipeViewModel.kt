package com.karamlyy.recipeapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecipeViewModel: ViewModel() {

    private val repository = RecipeRepository()

    val recipeData = MutableLiveData<Recipe>()
    val loading = MutableLiveData<Boolean>()

    fun fetchRecipe(query: String) {
        viewModelScope.launch {
            loading.value = true
            val recipe = repository.fetchRecipe(query)
            recipeData.value = recipe
            loading.value = false
        }
    }
}



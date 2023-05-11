package com.karamlyy.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView: SearchView = findViewById(R.id.searchView)
        val tvTitle: TextView = findViewById(R.id.tvTitle)
        val lvIngredients: ListView = findViewById(R.id.lvIngredients)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tvError: TextView = findViewById(R.id.tvError)
        val searchButton: Button = findViewById(R.id.searchButton)

        searchButton.setOnClickListener {
            val query = searchView.query.toString()
            viewModel.fetchRecipe(query)
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.fetchRecipe(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        viewModel.recipeData.observe(this) { recipe ->
            if (recipe != null) {
                tvTitle.text = recipe.label
                lvIngredients.adapter =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, recipe.ingredientLines)
                tvError.visibility = View.GONE
            } else {
                tvTitle.text = ""
                lvIngredients.adapter = null
                tvError.visibility = View.VISIBLE
                tvError.text = "No recipe found."
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }
}

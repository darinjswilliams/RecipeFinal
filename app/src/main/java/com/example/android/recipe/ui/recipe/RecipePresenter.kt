package com.example.android.recipe.ui.recipe

import android.view.View
import com.example.android.recipe.R
import com.example.android.recipe.data.model.Recipe
import com.example.android.recipe.data.model.local.RecipeStore
import com.example.android.recipe.databinding.ActivityRecipeBinding

class RecipePresenter(val store: RecipeStore, val binding: ActivityRecipeBinding) {

    private lateinit var recipe: Recipe

    fun loadRecipe(id: String){
         recipe = store.getRecipe(id)!!

        if(recipe == null) {
            binding.title.visibility = View.GONE
            binding.description.text = R.string.recipe_not_found.toString()
        }
    }
}
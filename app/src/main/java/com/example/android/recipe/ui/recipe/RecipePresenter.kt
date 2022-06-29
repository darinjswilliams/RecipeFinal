package com.example.android.recipe.ui.recipe

import com.example.android.recipe.data.model.Recipe
import com.example.android.recipe.data.model.local.Favorites
import com.example.android.recipe.data.model.local.RecipeStore

class RecipePresenter(val store: RecipeStore, val view: RecipeContract.View, val favorites: Favorites?):RecipeContract.Listener {

    private lateinit var recipe: Recipe

    fun loadRecipe(id: String){
         recipe = store.getRecipe(id)!!

        if(recipe == null) {
            //this will call the activitiy, because the activity implements the interface
            view.showRecipeNotFoundError()
        } else {
            view.setTitle(recipe.title)
            view.setDescription(recipe.description)
            view.setFavorite(recipe.id?.let { favorites?.get(it) })
        }
    }

    fun toggleFavorite() {
        if(recipe == null) {
            throw  IllegalStateException()
        }
        view.setFavorite( favorites?.toggle(recipe.id!!) == true )
    }
}
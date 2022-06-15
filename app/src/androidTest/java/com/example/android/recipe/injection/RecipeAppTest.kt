package com.example.android.recipe.injection

import com.example.android.recipe.data.model.local.Favorites
import com.example.android.recipe.data.model.local.InMemoryFavorites


class RecipeAppTest: RecipeApp() {
    //helper fields
    //mock help class
   private lateinit var SUT: RecipeApp

   //will use InMemoryFavorites
   private lateinit var favorites: Favorites

    fun setUp() {
        SUT = RecipeApp()
        favorites = InMemoryFavorites()
    }

    override fun getFavorites(): Favorites {
        return favorites
    }

    //region for test methods

    //region for private methods  
}
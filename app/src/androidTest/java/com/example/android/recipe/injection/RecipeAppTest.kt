package com.example.android.recipe.injection

import com.example.android.recipe.data.model.local.Favorites
import com.example.android.recipe.data.model.local.InMemoryFavorites
import org.junit.Test


class RecipeAppTest: RecipeApp() {
    //helper fields
    //mock help class
   private lateinit var SUT: RecipeApp

   //will use InMemoryFavorites
   private lateinit var favorites: Favorites

   @Test
    fun setUp() {
        SUT = RecipeApp()
    }

    override fun getFavorites(): Favorites {
        favorites = InMemoryFavorites()
        return favorites
    }

    //region for test methods

    //region for private methods  
}
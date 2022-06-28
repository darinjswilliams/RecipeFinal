package com.example.android.recipe.ui.recipe

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.recipe.data.model.local.Favorites
import com.example.android.recipe.data.model.local.RecipeStore
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class RecipePresenterTest {
    private lateinit var store: RecipeStore
    private lateinit var favorites: Favorites
    private lateinit var view: RecipeContract.View
    private lateinit var presenter: RecipePresenter

    @Before
    fun setup() {
        store = Mockito.mock(RecipeStore::class.java)
        favorites = Mockito.mock(Favorites::class.java)
        view = Mockito.mock(RecipeContract.View::class.java)
        presenter = RecipePresenter(store, view, favorites)
    }

    @Test
    fun recipeNotFounc(){
        Mockito.`when`(store.getRecipe(Mockito.anyString())).thenReturn(null)
        presenter.loadRecipe("no recipe")
        Mockito.verify(view, Mockito.times(1)).showRecipeNotFoundError()
    }



}
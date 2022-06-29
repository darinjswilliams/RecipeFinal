package com.example.android.recipe.ui.recipe

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.recipe.data.model.local.Favorites
import com.example.android.recipe.data.model.local.RecipeStore
import com.example.android.recipe.data.model.readFromStreams
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
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
        presenter.loadRecipe("no_such_recipe")
        Mockito.verify(view, Mockito.times(1)).showRecipeNotFoundError()
    }

    @Test(expected = UninitializedPropertyAccessException::class)
    fun toggleWithoutLoad(){
        presenter.toggleFavorite()
    }

    @Test
    fun loadWaterAndFavorite(){
        val stream = this.javaClass.classLoader?.getResourceAsStream("recipes/water.txt")?.bufferedReader()?.readLines()
        assertThat(stream, `is`(notNullValue()))
        val recipe = readFromStreams(stream)
        assertThat(recipe, `is`(notNullValue()))
        Mockito.`when`(store.getRecipe(Mockito.anyString())).thenReturn(recipe)
        Mockito.`when`(favorites.toggle(Mockito.anyString())).thenReturn(true)
        presenter.loadRecipe("water")
        presenter.toggleFavorite()

        val captor  = ArgumentCaptor.forClass(Boolean.javaClass)
        Mockito.verify(view, Mockito.times(2))
            .setFavorite(captor.capture())
        assertThat(captor.allValues.get(0), `is`(false))
        assertThat(captor.allValues.get(1), `is`(true))
    }


}
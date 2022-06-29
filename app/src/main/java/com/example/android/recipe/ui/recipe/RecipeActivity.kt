package com.example.android.recipe.ui.recipe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.recipe.R
import com.example.android.recipe.data.model.local.RecipeStore
import com.example.android.recipe.databinding.ActivityRecipeBinding
import com.example.android.recipe.injection.RecipeApp

class RecipeActivity : AppCompatActivity(), RecipeContract.View {

    private lateinit var binding: ActivityRecipeBinding

//    private val app by lazy { application }

    companion object {
        const val KEY_ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //step 1. setup the ui
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe)

        //setp 2 load recipe from store
        val store = RecipeStore(this, "recipes")

        val id = intent.getStringExtra(KEY_ID)

        //step 4 if recipe is not null show recipe, this is done in presenter
        val favorites = (application as? RecipeApp)?.getFavorites()

        val presenter = RecipePresenter(store, this, favorites)
        id?.let { presenter.loadRecipe(it) }


        //step 5 when title is clidk toggle favorites
        binding.title.setOnClickListener {
            presenter.toggleFavorite()
        }

    }

    override fun showRecipeNotFoundError() {
        binding.title.visibility = View.GONE
        binding.description.text = R.string.recipe_not_found.toString()
    }

    override fun setTitle(title: String?) {

        title?.let { binding.title.text = it }

    }


    override fun setDescription(description: String?) {
        description?.let { binding.description.text = it }
    }

    override fun setFavorite(favorite: Boolean) {
        favorite?.let { binding.title.isSelected = it     }
    }
}
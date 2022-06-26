package com.example.android.recipe.ui.recipe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.recipe.R
import com.example.android.recipe.data.model.local.RecipeStore
import com.example.android.recipe.databinding.ActivityRecipeBinding
import com.example.android.recipe.injection.RecipeApp
import timber.log.Timber

class RecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding

    private val app by lazy { application }

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

        val presenter = RecipePresenter(store, binding)
        id?.let{ presenter.loadRecipe(id) }

//        val recipe = id?.let { store.getRecipe(it) }
//        Timber.i("Here is the Recipe $recipe")

        //step 3, if recipe is null show error
//        if (recipe == null) {
//
//            return
//        }

        //step 4 if recipe is not null show recipe
        val favorites = (application as? RecipeApp)?.getFavorites()

        val favorite = recipe.let{ favorites?.get(id) }

        recipe.apply {

            binding.title.text = title
            if (favorite != null) {
                binding.title.isSelected = favorite
            }

            //step 5 when title is clidk toggle favorites
            binding.title.setOnClickListener {

                binding.title.isSelected =  favorites?.toggle(recipe.id!!) == true
            }

            binding.description.text = recipe.description
        }

    }
}
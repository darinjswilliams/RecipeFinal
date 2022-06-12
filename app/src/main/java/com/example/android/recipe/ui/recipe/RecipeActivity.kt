package com.example.android.recipe.ui.recipe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.recipe.R
import com.example.android.recipe.data.model.local.RecipeStore
import com.example.android.recipe.data.model.local.SharedPreferencesFavorites
import com.example.android.recipe.databinding.ActivityRecipeBinding
import timber.log.Timber

class RecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding

    companion object {
        const val KEY_ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe)

        val store = RecipeStore(this, "recipes")

        val id = intent.getStringExtra(KEY_ID)

        val recipe = id?.let { store.getRecipe(it) }
        Timber.i("Here is the Recipe $recipe")

        if (recipe == null) {
            binding.title.visibility = View.GONE
            binding.description.text = R.string.recipe_not_found.toString()
            return
        }

        val favorites = SharedPreferencesFavorites(this)

        val favorite = recipe.let{ favorites.get(id) }

        recipe.apply {

            binding.title.text = title
            binding.title.isSelected = favorite

            binding.title.setOnClickListener {
                val result = favorites.toggle(recipe.id!!)
                binding.title.isSelected = result
            }

            binding.description.text = recipe.description
        }

    }
}
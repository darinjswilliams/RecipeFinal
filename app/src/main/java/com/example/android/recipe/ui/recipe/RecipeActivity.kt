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

        val favorites = (application as? RecipeApp)?.getFavorites()

        val favorite = recipe.let{ favorites?.get(id) }

        recipe.apply {

            binding.title.text = title
            if (favorite != null) {
                binding.title.isSelected = favorite
            }

            binding.title.setOnClickListener {

                binding.title.isSelected =  favorites?.toggle(recipe.id!!) == true
            }

            binding.description.text = recipe.description
        }

    }
}
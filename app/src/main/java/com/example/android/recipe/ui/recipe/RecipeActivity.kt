package com.example.android.recipe.ui.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.recipe.R
import com.example.android.recipe.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {

private lateinit var binding: ActivityRecipeBinding

companion object {
    const val KEY_ID = "id"
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe)

    }
}
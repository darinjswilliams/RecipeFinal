package com.example.android.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.recipe.adapter.RecipeAdapter
import com.example.android.recipe.data.model.local.RecipeStore
import com.example.android.recipe.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivityMainBinding =
            ActivityMainBinding.inflate(layoutInflater)

        val store = RecipeStore(this, "recipes")

        binding.recipes.adapter = RecipeAdapter(store, RecipeAdapter.OnClickListener{
           Timber.i("here is the data $it")
        })

        binding.recipes.setHasFixedSize(true)


    }
}
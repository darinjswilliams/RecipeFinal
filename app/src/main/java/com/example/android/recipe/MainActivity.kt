package com.example.android.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.recipe.adapter.RecipeAdapter
import com.example.android.recipe.data.model.local.RecipeStore
import com.example.android.recipe.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val store = RecipeStore(this, "recipes")

        //adapters
        binding.recipes.adapter = RecipeAdapter(store, RecipeAdapter.OnClickListener {
            Timber.i("Click on Recipe $it")
        })

        binding.recipes.setHasFixedSize(true)

        //refresh the ui with the data
        binding.invalidateAll()

        }
    }
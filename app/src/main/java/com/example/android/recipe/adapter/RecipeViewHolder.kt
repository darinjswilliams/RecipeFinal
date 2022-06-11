package com.example.android.recipe.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.android.recipe.data.model.Recipe
import com.example.android.recipe.databinding.RecipeViewItemBinding

class RecipeViewHolder(private var binding: RecipeViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(recipe: Recipe) {
        binding.recipeProperty = recipe
        binding.executePendingBindings()
    }
}
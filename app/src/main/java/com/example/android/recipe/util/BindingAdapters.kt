package com.example.android.recipe.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.recipe.adapter.RecipeAdapter
import com.example.android.recipe.data.model.Recipe
import timber.log.Timber

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Recipe>?){
    Timber.i("*** bindingRecyclerView Called  ${data?.size} **")
    val adapter = recyclerView.adapter as RecipeAdapter
    adapter.submitList(data)
}
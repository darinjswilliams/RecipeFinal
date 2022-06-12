package com.example.android.recipe.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.recipe.data.model.Recipe
import com.example.android.recipe.data.model.local.RecipeStore
import com.example.android.recipe.databinding.RecipeViewItemBinding
import com.example.android.recipe.ui.recipe.RecipeActivity
import com.example.android.recipe.ui.recipe.RecipeActivity.Companion.KEY_ID

class RecipeAdapter(private val store: RecipeStore, private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    private lateinit var binding: RecipeViewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        binding = RecipeViewItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = store.recipes[position]
        holder.itemView.setOnClickListener{
            onClickListener.onClick(recipe)
            val context = holder.itemView.context
            val intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra(KEY_ID, recipe.id)
            context.startActivity(intent)
        }
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = store.recipes.size


    class OnClickListener(val clickListener: (recipe: Recipe) -> Unit){
        fun onClick(recipe: Recipe) = clickListener(recipe)
    }

}

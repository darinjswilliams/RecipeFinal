package com.example.android.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.recipe.data.model.Recipe
import com.example.android.recipe.data.model.local.RecipeStore
import com.example.android.recipe.databinding.RecipeViewItemBinding
import timber.log.Timber

class RecipeAdapter(private val store: ArrayList<Recipe>, private val onClickListener: OnClickListener) :
    ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(DiffCallback) {

    class RecipeViewHolder(private var binding: RecipeViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.recipeProperty = recipe
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return  RecipeViewHolder(RecipeViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
//        val recipeProperty = getItem(position)
        val recipe = store[position]

        Timber.i("current position of recipe ${position}")
        holder.itemView.setOnClickListener {
            onClickListener.onClick(recipe)
        }
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        Timber.i("Here is the size ${store.size}")
        return store.size
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return newItem == oldItem
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return newItem.id == oldItem.id
        }

    }

    override fun onCurrentListChanged(
        previousList: MutableList<Recipe>,
        currentList: MutableList<Recipe>
    ) {
        super.onCurrentListChanged(previousList, currentList)
    }

    class OnClickListener(val clickListener: (recipe: Recipe) -> Unit){
        fun onClick(recipe: Recipe) = clickListener(recipe)
    }
}

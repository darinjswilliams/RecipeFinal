package com.example.android.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.recipe.adapter.RecipeAdapter
import com.example.android.recipe.data.model.Recipe
import com.example.android.recipe.data.model.local.RecipeStore
import com.example.android.recipe.databinding.ActivityMainBinding
import com.example.android.recipe.util.bindRecyclerView
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.UserAdapter
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivityMainBinding =
            ActivityMainBinding.inflate(layoutInflater)

        val store = RecipeStore(this, "recipes")
        val rList = store.recipes
        Timber.i("List of recipes ${rList.size}")
        rList.forEach() {
            Timber.i("Recipe Id ${it.id}")
            Timber.i("Title ${it.title}")
        }


//        val adapter: UserAdapter<com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User> =
//            UserAdapter()
//        viewModel.usersList.observe(this) { list -> adapter.submitList(list) }
//        recyclerView.setAdapter(adapter)

            binding.recipes.adapter = RecipeAdapter(store.recipes, RecipeAdapter.OnClickListener {
                Timber.i("here is the data $it")

            })


//        binding.recipes.adapter.

            binding.recipes.setHasFixedSize(true)
        }
    }
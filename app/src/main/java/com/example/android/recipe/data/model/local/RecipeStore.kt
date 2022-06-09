package com.example.android.recipe.data.model.local

import android.content.Context
import android.content.res.AssetManager
import com.example.android.recipe.data.model.Recipe
import com.example.android.recipe.data.model.readFromStreams
import timber.log.Timber
import java.io.File
import java.io.InputStream

class RecipeStore(val context: Context, val directory: String) {


    val recipes = arrayListOf<Recipe>()
    val mapLookUp = hashMapOf<String, Recipe>()

 init {
        val streams = getAssetStreams(context.assets, directory)

        streams.forEach{
            val recipe = readFromStreams(it.bufferedReader().readLines())
            Timber.i("Reading files $it")
            if(recipe != null){
                recipes.add(recipe)
                mapLookUp.put(recipe.id.toString(), recipe)
            }
        }
    }

    fun getRecipe(id: String): Recipe? {
        return mapLookUp.get(id)
    }

    companion object Store {
        fun getAssetStreams(manager: AssetManager, directory: String): List<InputStream> {
            val fileNames = getAllFileNames(manager, directory)
            val streams = arrayListOf<InputStream>()

            fileNames?.forEach {
                val file = File(directory, it)

                try {
                    val inStream = manager.open(file.path)
                    streams.add(inStream)
                } catch (e: Exception) {
                    Timber.e("Exception ${e.localizedMessage}")
                }
            }

            return streams
        }

        fun getAllFileNames(manager: AssetManager, directory: String): Array<String>? {

            return try {
                manager.list(directory)
            } catch (e: Exception) {
                emptyArray()
            }
        }
    }
}
package com.example.android.recipe.injection

import android.app.Application
import com.example.android.recipe.data.model.local.Favorites
import com.example.android.recipe.data.model.local.SharedPreferencesFavorites
import com.facebook.stetho.Stetho
import timber.log.Timber

open class RecipeApp : Application() {


    private lateinit var favorites: Favorites


    override fun onCreate() {
        super.onCreate()


        //Timber
        Timber.plant(Timber.DebugTree())

        //Stetho
        Stetho.initializeWithDefaults(this@RecipeApp)

        favorites = SharedPreferencesFavorites(this@RecipeApp)
    }


    open fun getFavorites(): Favorites {

        return favorites
    }

}
package com.example.android.recipe.injection

import android.app.Application
import com.example.android.recipe.data.model.local.Favorites
import com.example.android.recipe.data.model.local.SharedPreferencesFavorites
import com.facebook.stetho.Stetho
import timber.log.Timber

class RecipeApp : Application() {


    val favorites: Favorites by lazy { SharedPreferencesFavorites(this) }

    override fun onCreate() {
        super.onCreate()

        //Timber
        Timber.plant(Timber.DebugTree())

        //Stetho
        Stetho.initializeWithDefaults(this@RecipeApp)
    }

    fun getFavorites(): Favorites {

        favorites = SharedPreferencesFavorites(this)

        return favorites
    }

}